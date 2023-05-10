public class Computer {
    public final String name;
    public final String vendor;
    public Processor processor;
    public RAM ram;
    public HardDisk hardDisk;
    public Display display;
    public Keyboard keyboard;

    public Computer(String name, String vendor, Processor processor, RAM ram,
                    HardDisk hardDisk, Display display, Keyboard keyboard) {
        this.name = name;
        this.vendor = vendor;
        this.processor = processor;
        this.ram = ram;
        this.hardDisk = hardDisk;
        this.display = display;
        this.keyboard = keyboard;
    }

    public Processor getProcessor() {
        return processor;
    }

    public Computer setProcessor(Processor processor) {
        return new Computer(name, vendor, processor, ram, hardDisk, display, keyboard);
    }

    public RAM getRAM() {
        return ram;
    }

    public Computer setRAM(RAM ram) {
        return new Computer(name, vendor, processor, ram, hardDisk, display, keyboard);
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    public Computer setHardDisk(HardDisk hardDisk) {
        return new Computer(name, vendor, processor, ram, hardDisk, display, keyboard);
    }

    public Display getDisplay() {
        return display;
    }

    public Computer setDisplay(Display display) {
        return new Computer(name, vendor, processor, ram, hardDisk, display, keyboard);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Computer setKeyboard(Keyboard keyboard) {
        return new Computer(name, vendor, processor, ram, hardDisk, display, keyboard);
    }

    public double weightComputer() {
        return processor.getWeight() + ram.getWeight() + hardDisk.getWeight() + display.getWeight() + keyboard.getWeight();
    }

    public String toString() {
        return "Имя: " + name + " , Производитель: " + vendor + "\n"
                + "Комплектующие: " + "\n"
                + "Процессор: " + processor + "\n"
                + "Оперативная память: " + ram + "\n"
                + "Накопитель информации: " + hardDisk + "\n"
                + "Экран: " + display + "\n"
                + "Клавиатура: " + keyboard + "\n"
                + "-------------------------------------------" + "\n"
                + "Общий вес комплектующих: " + weightComputer() + " кг.";
    }

}
