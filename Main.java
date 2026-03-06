import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SalleDeSport salle = new SalleDeSport();

        // 1. Créer 3 prestations
        Prestation pSauna = new Prestation("SAUNA", "Accès Sauna", 5.0);
        Prestation pCoach = new Prestation("COACH", "Coach Personnel", 25.0);
        Prestation pServiette = new Prestation("SERVIETTE", "Location Serviette", 2.0);

        // 2. Créer 3 séances
        Seance s1 = new Seance(1, "BodyPump", LocalDateTime.now().plusDays(2), 20);
        Seance s2 = new Seance(2, "Yoga", LocalDateTime.now().plusDays(5), 15);
        Seance s3 = new Seance(3, "CrossFit", LocalDateTime.now().minusDays(1), 10);
        
        salle.ajouterSeance(s1);
        salle.ajouterSeance(s2);
        salle.ajouterSeance(s3);

        // 3. Créer 2 adhérents (1 Basic, 1 Premium)
        Abonnement aboBasic = new AbonnementBasic("REF123", LocalDate.now(), 12, 30.0);
        Adherent a1 = new Adherent(101, "Alice", aboBasic);
        
        Abonnement aboPremium = new AbonnementPremium("REF456", LocalDate.now(), 12, 50.0, 5);
        Adherent a2 = new Adherent(102, "Bob", aboPremium);

        salle.ajouterAdherent(a1);
        salle.ajouterAdherent(a2);

        // 4. Créer des réservations
        Reservation resA1_S1 = a1.reserver(s1);
        resA1_S1.ajouterPrestation(pServiette); 

        Reservation resA2_S2 = a2.reserver(s2);
        resA2_S2.ajouterPrestation(pSauna); 
        resA2_S2.ajouterPrestation(pCoach); 
        
        Reservation resA2_S3 = a2.reserver(s3); 

        // Annuler une réservation
        Reservation resA1_S2 = a1.reserver(s2);
        resA1_S2.ajouterPrestation(pSauna);
        resA1_S2.annuler(); 

        // 5. Affichages
        System.out.println("--- Liste des adhérents + abonnement ---");
        System.out.println(a1);
        System.out.println(a2);

        System.out.println("\n--- Réservations futures de Bob (Premium) ---");
        for (Reservation r : a2.reservationsFutures()) {
            System.out.println(r);
        }

        System.out.println("\n--- Adhérents ayant accès au sauna ---");
        for (Adherent a : salle.adherentsAvecSauna()) {
            System.out.println(a.getNom());
        }

        System.out.println("\n--- Chiffre d'affaires des prestations ---");
        System.out.println(salle.chiffreAffairesPrestations() + "€");
        
        // Test de l'exception
        System.out.println("\n--- Recherche d'adhérent ---");
        try {
            Adherent trouvé = salle.trouverAdherent(999);
            System.out.println(trouvé);
        } catch (AdherentNonTrouveException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}