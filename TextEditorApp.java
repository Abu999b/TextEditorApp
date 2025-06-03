import java.util.Scanner;

class TextEditor {
    private String[] stack;
    private int top;
    private int capacity;
    private String currentText;

    // Constructor
    TextEditor(int size) {
        stack = new String[size];
        top = -1;
        capacity = size;
        currentText = "";
    }

    // Save current state before editing
    private void saveState() {
        if (top < capacity - 1) {
            stack[++top] = currentText;
        } else {
            System.out.println("Undo history full. Cannot save more states.");
        }
    }

    // Type text (append)
    public void type(String newText) {
        saveState();
        currentText += newText;
        System.out.println("Text added.");
    }

    // Undo last change
    public void undo() {
        if (top == -1) {
            System.out.println("Nothing to undo.");
        } else {
            currentText = stack[top--];
            System.out.println("Undo successful.");
        }
    }

    // Show current content
    public void showContent() {
        System.out.println("Current Content: \"" + currentText + "\"");
    }
}

public class TextEditorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor(10);  // Can store up to 10 undo states

        int choice;
        do {
            System.out.println("\n--- Mini Text Editor ---");
            System.out.println("1. Type Text");
            System.out.println("2. Undo");
            System.out.println("3. Show Content");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text to add: ");
                    String text = scanner.nextLine();
                    editor.type(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.showContent();
                    break;
                case 4:
                    System.out.println("Exiting editor...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
