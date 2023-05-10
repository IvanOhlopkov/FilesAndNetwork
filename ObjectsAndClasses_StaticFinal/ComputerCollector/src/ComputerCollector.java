public class ComputerCollector {
    public static void main(String[] args) {
        Processor processorOne = new Processor(2.3, 2, "Intel", 0.265);
        RAM ramOne = new RAM("DDR", 4, 0.290);
        HardDisk hardDiskOne = new HardDisk(HardDiskType.SSD, 256, 0.100);
        Display displayOne = new Display(21, DisplayType.IPS,2.150);
        Keyboard keyboardOne = new Keyboard("Мембранная", KeyboardHasLight.НЕТ, 0.410);
        Computer computerOne = new Computer("Рабочий", "Lenovo", processorOne, ramOne, hardDiskOne,
                displayOne, keyboardOne);
        System.out.println(computerOne);

        System.out.println("ПК 2:");
        Processor processorTwo = new Processor(2.1, 4, "Intel", 0.280);
        RAM ramTwo = new RAM("DDR", 16, 0.290);
        Keyboard keyboardTwo = new Keyboard("Механическая", KeyboardHasLight.ДА, 0.630);
        Computer computerTwo = new Computer("Игровой", "Red Magic", processorTwo, ramTwo, hardDiskOne,
                displayOne, keyboardTwo);
        System.out.println(computerTwo);
    }
}
