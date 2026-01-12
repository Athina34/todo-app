# College To-Do App
Μια ολοκληρωμένη εφαρμογή διαχείρισης εργασιών (Task Management) για φοιτητές, 
αναπτυγμένη σε περιβάλλον Spring Boot.

Γλώσσα Προγραμματισμού: Java 21.

Framework: Spring Boot 3.4.1.

Template Engine: Thymeleaf (για τα δυναμικά HTML αρχεία).

Security: Spring Security με κρυπτογράφηση BCrypt.

Βάση Δεδομένων: MySQL.

Άλλα εργαλεία: Spring Data JPA, Lombok, Maven.

3. Βασικά Χαρακτηριστικά

Σύστημα Αυθεντικοποίησης: Πλήρες σύστημα Login και Register με ασφάλεια.

Διαχείριση Ρόλων (RBAC): Διαχωρισμός δικαιωμάτων μεταξύ απλών χρηστών και Admin.

Dashboard Φοιτητή: Οπτική απεικόνιση εκκρεμών εργασιών και εργασιών υψηλής προτεραιότητας.

Αρχικοποίηση Δεδομένων: Αυτόματη δημιουργία ρόλων κατά την εκκίνηση της εφαρμογής μέσω της κλάσης DataInitializer.

## Ρύθμιση Βάσης Δεδομένων
Για να τρέξει η εφαρμογή, απαιτείται βάση δεδομένων MySQL με τις εξής ρυθμίσεις:
* URL:`jdbc:mysql://localhost:3306/todo_db`
* Username:`todo_user`
* Password:`Sweet_Athina2005!`
