package brickset;

import repository.Repository;
import java.util.Comparator;


/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Kiírja az összes SpongyaBob Kockanadrág témájú Lego készlet nevét.
     */
    public void printAllSWThemedLegoSets() {
        getAll().stream().
                filter(lego -> lego.getTheme().equals("SpongeBob SquarePants")).
                map(LegoSet::getName).
                forEach(System.out::println);
    }

    /**
     * Kiírja a legtöbb darabból álló Lego set nevét.
     */
    public void printSetsWithMostPieces(int n){
        getAll().stream()
                .sorted(Comparator.comparingInt(LegoSet::getPieces).reversed())
                .limit(n)
                .map(LegoSet::getName)
                .forEach(System.out::println);
    }

    /**
     * Visszaadja, hogy hány darab Lego set van a megadott csomagolásban.
     */
    public long countSetsWithPackagingType(String p){
        return getAll().stream()
                .filter(legoSet -> legoSet.getPackagingType() == PackagingType.valueOf(p.toUpperCase()))
                .map(LegoSet::getNumber)
                .count();
    }

    /**
     * A Lego darabok átlagos számát adja vissza.
     */
    public double getAveragePieceNumber() {
        return getAll().stream().
                mapToDouble(LegoSet::getPieces).average().getAsDouble();
    }

    /**
     * Kiírja azoknak a lego seteknek a nevét, amelyek a megadott darabszámok közé esnek.
     */
    public void printSetsBetweenGivenPieces(int x, int y){
        getAll().stream()
                .filter(legoSet -> legoSet.getPieces() > x && legoSet.getPieces() < y)
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        repository.printAllSWThemedLegoSets();
        repository.printSetsWithMostPieces(1);
        System.out.println(repository.countSetsWithPackagingType("Box"));
        System.out.println("Átlagos darabszám: " + repository.getAveragePieceNumber());
        repository.printSetsBetweenGivenPieces(250, 500);

    }

}