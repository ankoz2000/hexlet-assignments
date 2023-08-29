package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList list = new SafetyList();
        ListThread l1 = new ListThread(list);
        ListThread l2 = new ListThread(list);

        l1.start();
        l2.start();

        try {
            l1.join();
            l2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Elems count: " + list.getSize());
        // END
    }
}

