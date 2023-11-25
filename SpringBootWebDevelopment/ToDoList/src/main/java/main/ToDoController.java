package main;

import main.model.Task;
import main.model.TaskRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class ToDoController {

    @Autowired
    private TaskRepository taskRepository;

    private volatile List<Task> tasks;

    @RequestMapping("/")
    public String index() {
        return (new Date()).toString();
    }

    @PostMapping(value = "/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask(@RequestBody Task taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDone(false);
        task.setCreationTime(LocalDateTime.now());
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping(value = "/tasks/ID")
    public ResponseEntity<String> getTaskInfo(@RequestBody Task taskRequest) {
        Optional<Task> taskOptional = taskRepository.findById(taskRequest.getId());
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskOptional.toString());
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PatchMapping("/tasks/{ID}")
    public ResponseEntity<String> updateTask(@PathVariable int ID, @RequestBody Task taskRequest) {
        Optional<Task> taskOptional = taskRepository.findById(ID);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task not found");
        }
        Task task = taskOptional.get();
        task.setDone(taskRequest.isDone());

        if (!Strings.isEmpty(taskRequest.getTitle())) {
            task.setTitle(taskRequest.getTitle());
        }
        if (!Strings.isEmpty(taskRequest.getDescription())) {
            task.setDescription(taskRequest.getDescription());
        }
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/tasks/{ID}")
    public ResponseEntity<String> deleteTask(@PathVariable int ID) {
        Optional<Task> taskOptional = taskRepository.findById(ID);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("task not found");
        }
        Task task = taskOptional.get();
        taskRepository.delete(task);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
