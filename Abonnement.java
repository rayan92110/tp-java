import java.time.LocalDate;

public abstract class Abonnement {
    private String reference;
    private LocalDate dateDebut;
    private int dureeMois;
    private double prixMensuel;

    public Abonnement(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dureeMois = dureeMois;
        this.prixMensuel = prixMensuel;
    }

    public LocalDate dateFin() {
        return dateDebut.plusMonths(dureeMois);
    }

    public double coutTotal() {
        return prixMensuel * dureeMois;
    }

    public abstract boolean permetAccesSauna();
    public abstract int creditsCoachInclus();

    @Override
    public String toString() {
        return "Réf: " + reference + " | Fin: " + dateFin() + " | Coût total: " + coutTotal() + "€";
    }
}