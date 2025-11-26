package user;

import java.time.LocalDate;

public class User extends Person {

    private int userId;
    private long userIdNumber;
    private String password;
    private String role;
    private LocalDate lastPasswordChange;
    private int failedLoginAttempts = 0;
    private boolean isAccountLocked = false;

    public User(int userId, long idNumber, String password,
            String role, LocalDate lastPasswordChange, int failedLoginAttempts, boolean isAccountLocked,
            String names, String lastNames, int phone) {
        super(names, lastNames, idNumber, phone);
        this.userId = userId;
        this.userIdNumber = idNumber;
        this.password = password;
        this.role = role;
        this.lastPasswordChange = lastPasswordChange;
        this.failedLoginAttempts = failedLoginAttempts;
        this.isAccountLocked = isAccountLocked;
    }

    public User(User user) {
        super(user.names, user.lastNames, user.userIdNumber, user.phone);
        this.userIdNumber = user.userIdNumber;
        this.password = user.password;
        this.role = user.role;
        this.lastPasswordChange = user.lastPasswordChange;
    }

    public long getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(long userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.lastPasswordChange = LocalDate.now();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(LocalDate lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean isAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(boolean isAccountLocked) {
        this.isAccountLocked = isAccountLocked;
    }

    public LocalDate getPasswordExpirationDate() {
        return lastPasswordChange.plusDays(60);
    }

    public boolean shouldChangePassword() {
        return lastPasswordChange.plusDays(60).isBefore(LocalDate.now());
    }

    public static Integer tryParsePhone(String phone) {
        int result;
        try {
            result = Integer.parseInt(phone);
        } catch (NumberFormatException e) {
            return null;
        }
        return isValidPhone(result) ? result : null;
    }

    public static Long tryParseUserId(String userId) {
        long result;
        try {
            result = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            return null;
        }
        return isValidDni(userId) ? result : null;
    }

    public static String formatIsAccountLocked(boolean isAccountLocked) {
        return isAccountLocked ? "Inactive" : "Active";
    }

    public static boolean parseIsAccountLocked(String isAccountLockedString) {
        return !isAccountLockedString.equals("Inactive");
    }

    public static boolean isValidIdNumber(String user) {
        return user.matches("\\d{8}") || user.matches("\\d{11}");
    }

    public static boolean isValidDni(String dni) {
        return dni.matches("\\d{8}");
    }

    public static boolean isValidPassword(String password) {
        boolean isLongEnough = password.length() > 4;
        return password.matches("[A-Za-z0-9]+") && isLongEnough && password.length() < 31;
    }

    public static boolean isValidPhone(int phone) {
        String phoneMatch = Integer.toString(phone);
        return phoneMatch.matches("\\d{6}") || phoneMatch.matches("\\d{9}");
    }

    public static boolean isTooLong(String text) {
        return text.length() > 20;
    }
}
