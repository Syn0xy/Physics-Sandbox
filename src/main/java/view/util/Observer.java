package view.util;

/**
 * This interface is used to help the program to be based on MVC architecture.
 * Alls classes implementing this interface will be identify as a view.
 * @see Subject
 */
public interface Observer {
        public void update(Subject subj);
        public void update(Subject subj, Object data);
}
