public class Main {
    private static final String SVC = "RegistryLookup_74c9a5";
    static class AppException extends RuntimeException { final String code; AppException(String code, String msg) { super(msg); this.code = code; } }
    static class NotFound extends AppException { NotFound(String id) { super("NOT_FOUND", "Resource " + id + " not found"); } }
    static class Conflict extends AppException { Conflict(String id) { super("CONFLICT", "Resource " + id + " already exists"); } }
    static String lookup(String id) { if (id.startsWith("x-")) throw new NotFound(id); if (id.startsWith("d-")) throw new Conflict(id); return "value-" + id; }
    public static void main(String[] args) { for (String id : new String[]{"a-1", "x-2", "d-3"}) { try { System.out.printf("[%s] %s -> %s%n", SVC, id, lookup(id)); } catch (AppException e) { System.out.printf("[%s] ERROR %s: %s%n", SVC, e.code, e.getMessage()); } } }
}
