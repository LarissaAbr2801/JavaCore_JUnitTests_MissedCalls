package calls;

import java.util.*;
import java.time.LocalDateTime;

public class MissedCalls {

    private Map<LocalDateTime, String> missedCalls;

    public MissedCalls(Map<LocalDateTime, String> missedCalls) {
        this.missedCalls = missedCalls;
    }

    public boolean isMapEmpty() throws NullPointerException {
        return missedCalls.isEmpty();
    }

    public void addMissedCall(String phoneNumber,  Map<String, Contact> phoneBook) throws NullPointerException {
        for (Map.Entry<String, Contact> entry : phoneBook.entrySet()) {
            if (entry.getKey().equals(phoneNumber)) {
                missedCalls.put(LocalDateTime.now(), entry.getValue().getName() + " "
                        + entry.getValue().getSurname());
            } else {
                missedCalls.put(LocalDateTime.now(), phoneNumber);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<LocalDateTime, String> entry : missedCalls.entrySet()) {
            sb.append(String.format("%-30s%-30s", entry.getKey(), entry.getValue()) + "\n");
        }

        return sb.toString();
    }


    public void clearMissedCalls() throws NullPointerException{
        missedCalls.clear();
    }
}