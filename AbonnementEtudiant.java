import java.time.LocalDate;

public class AbonnementEtudiant extends Abonnement {
    private double reductionPourcent;

    public AbonnementEtudiant(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, double reductionPourcent) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.reductionPourcent = reductionPourcent;
    }

    @Override
    public double coutTotal() {
        double totalInitial = super.coutTotal();
        return totalInitial - (totalInitial * (reductionPourcent / 100.0));
    }

    @Override
    public boolean permetAccesSauna() { return false; }

    @Override
    public int creditsCoachInclus() { return 0; }
    
    @Override
    public String toString() {
        return "[ETUDIANT] " + super.toString() + " (Réduction: " + reductionPourcent + "%)";
    }
}