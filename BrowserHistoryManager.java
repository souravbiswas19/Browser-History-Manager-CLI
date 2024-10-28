
import java.util.Scanner;

class Page {

    String url;
    Page prev;
    Page next;

    public Page(String url) {
        this.url = url;
        this.prev = null;
        this.next = null;
    }
}

class History {

    private Page currentPage;

    // Visit a new page
    public void visitPage(String url) {
        Page newPage = new Page(url);
        if (currentPage != null) {
            // Set the new page's previous link to the current page
            newPage.prev = currentPage;
            // Update currentPage's next link to the new page (retaining forward pages)
            currentPage.next = newPage;
        }
        currentPage = newPage;
        System.out.println("Visited: " + url);
    }

    // Go back to the previous page
    public void goBack() {
        if (currentPage == null || currentPage.prev == null) {
            System.out.println("No previous page to go back to.");
        } else {
            currentPage = currentPage.prev;
            System.out.println("Went back to: " + currentPage.url);
        }
    }

    // Go forward to the next page
    public void goForward() {
        if (currentPage == null || currentPage.next == null) {
            System.out.println("No forward page to go to.");
        } else {
            currentPage = currentPage.next;
            System.out.println("Went forward to: " + currentPage.url);
        }
    }

    // Display the browsing history
    public void viewHistory() {
        if (currentPage == null) {
            System.out.println("No browsing history available.");
            return;
        }

        Page firstPage = currentPage;
        // Traverse to the first page in history
        while (firstPage.prev != null) {
            firstPage = firstPage.prev;
        }

        System.out.println("Browsing History:");
        while (firstPage != null) {
            if (firstPage == currentPage) {
                System.out.println("-> " + firstPage.url + " (current)");
            } else {
                System.out.println("   " + firstPage.url);
            }
            firstPage = firstPage.next;
        }
    }

    // Clear the browsing history
    public void clearHistory() {
        currentPage = null;
        System.out.println("Browsing history cleared.");
    }
}

public class BrowserHistoryManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        History browserHistory = new History();
        boolean exit = false;

        System.out.println("Browser History Manager");
        System.out.println("Commands:");
        System.out.println("1. Visit a new page");
        System.out.println("2. Go back to previous page");
        System.out.println("3. Go forward to next page");
        System.out.println("4. View browsing history");
        System.out.println("5. Clear browsing history");
        System.out.println("6. Exit");

        while (!exit) {
            System.out.print("\nEnter a command: ");
            int command = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (command) {
                case 1:
                    System.out.print("Enter the URL of the new page: ");
                    String url = scanner.nextLine();
                    browserHistory.visitPage(url);
                    break;
                case 2:
                    browserHistory.goBack();
                    break;
                case 3:
                    browserHistory.goForward();
                    break;
                case 4:
                    browserHistory.viewHistory();
                    break;
                case 5:
                    browserHistory.clearHistory();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the Browser History Manager.");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        scanner.close();
    }
}
