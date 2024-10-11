public class TodoListManager {
    public static final boolean EXTENSION_FLAG = false;

    public static void main(String[] args) throws FileNotFoundException {
        List<String> todos = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        String response = "";
        System.out.println("Welcome to your TODO List Manager!");
        
        while(!(response.equalsIgnoreCase("q"))) {
            // Prompt user
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");
            // Interpret response
            response = console.nextLine(); 
            if (response.equalsIgnoreCase("A")) {
                addItem(console, todos);
            } else if (response.equalsIgnoreCase("M")) {
                markItemAsDone(console, todos);
            } else if (response.equalsIgnoreCase("L")) {
                loadItems(console, todos);
            } else if (response.equalsIgnoreCase("S")) {
                saveItems(console, todos);
            } else if (!response.equalsIgnoreCase("Q")) {
                System.out.println("Unknown input: " + response);
                printTodos(todos);
            } 
        }
    }

    // This method prints out each item in the todo list, numbered.
    // If the TODO list is empty, the method will print the message,
    // "You have nothing to do yet today! Relax!"
    // Parameter(s): a List of TODO items
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if (todos.isEmpty()) {
                System.out.println("You have nothing to do yet today! Relax!");
        } else {
            for (int i = 0; i < todos.size(); i++) {
                System.out.println("  " + (i+1) + ": " + todos.get(i));
		    }
        }
    }

    // This method allows the user to add to the TODO list either at a specific index
    // or to the end of the list.
    // When the EXTENSION_FLAG is true, the user can add multiple items at once.
    // Parameter(s): a console Scanner, and a List of TODO items
    public static void addItem(Scanner console, List<String> todos) {
        if (EXTENSION_FLAG) {
            System.out.print("How many items would you like to add? ");
            String count = console.nextLine();
            int numItems = Integer.parseInt(count);
            for (int i = 0; i < numItems; i++) {
                System.out.print("What would you like to add? ");
                String addedItem = console.nextLine(); 
                if (todos.isEmpty()) {
                    todos.add(addedItem);
                } else {
                    System.out.print("Where in the list should it be " + "(1-" + 
                    (todos.size() + 1) + ")? " +  "(Enter for end): " );  
                    String itemIndex = console.nextLine();
                    if (itemIndex.equals("")) {
                        todos.add(addedItem);
                    } else {
                        int index = Integer.parseInt(itemIndex) - 1;
                        todos.add(index, addedItem);
                    }
                }
            }
        } else {
            System.out.print("What would you like to add? ");
            String addedItem = console.nextLine(); 
            if (todos.isEmpty()) {
                todos.add(addedItem);
            } else {
                System.out.print("Where in the list should it be " + "(1-" + 
                (todos.size() + 1) + ")? " +  "(Enter for end): " );  
                String itemIndex = console.nextLine();
                if (itemIndex.equals("")) {
                    todos.add(addedItem);
                } else {
                    int index = Integer.parseInt(itemIndex) - 1;
                    todos.add(index, addedItem);
                }
            }
        }
        printTodos(todos);
    }

    // This method allows the user to mark an item as completed, 
    // and removes the item from the list.
    // If the TODO list is empty, the method will print the message,
    // "All done! Nothing left to mark as done!"
    // Parameter(s): a console Scanner, and a List of TODO items
    public static void markItemAsDone(Scanner console, List<String> todos) {
        if (!todos.isEmpty()) {
            System.out.print("Which item did you complete (1-" + todos.size() + ")? ");
            String itemIndex = console.nextLine();
            int index = Integer.parseInt(itemIndex) - 1;
            todos.remove(index);
        } else if (todos.isEmpty()) {
            System.out.println("All done! Nothing left to mark as done!");
        }
        printTodos(todos);
    }

    // This method allows the user to load a list of items from a file 
    // and adds the file contents to the TODO list. 
    // Parameter(s): a console Scanner, and a List of TODO items
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        todos.removeAll(todos);
        System.out.print("File name? ");
		String fileName = console.nextLine();
        Scanner scanFile = new Scanner(new File(fileName));
        while(scanFile.hasNextLine()) {
            todos.add(scanFile.nextLine());
        }
        printTodos(todos);
    }

    // This method allows the user to print the TODO list to a file
    // Parameter(s): a console Scanner, and a List of TODO items
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
		String outFile = console.nextLine();
		PrintStream output = new PrintStream(new File(outFile));
		for (int i = 0; i < todos.size(); i++) {
			output.println(todos.get(i));
		}
        printTodos(todos);
    }
}
