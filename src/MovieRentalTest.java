import org.junit.*;

public class MovieRentalTest extends junit.framework.TestCase {

    Movie theManWhoKnewTooMuch, mulan, slumdogMillionaire;
    @Before
    public void setUp() {
        theManWhoKnewTooMuch = new RegularMovie("The Man Who Knew Too Much");
        mulan = new ChildrensMovie("Mulan");
        slumdogMillionaire = new NewReleaseMovie("Slumdog Millionaire");
    }

    @Test
    public void testGetDaysRented() {
        assertEquals(2, new MovieRental(theManWhoKnewTooMuch, 2).getDaysRented());
    }

    @Test
    public void testGetMovie() {
        assertEquals(theManWhoKnewTooMuch, new MovieRental(theManWhoKnewTooMuch, 2).getMovie());
    }

    @Test
    public void testGetName() {
        String name = "John Doe"; 
        assertEquals(name, new Customer(name)._name);
    }

    @Test
    public void testStatementRegularMovieOnly() {
        // regular movies cost $2.00 for the first 2 days, and $1.50/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer janeDoe = new Customer("Jane Doe");
        janeDoe.addMovieRental(new MovieRental(theManWhoKnewTooMuch, 1));
        assertEquals("Rental Record for Jane Doe\n" +
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(theManWhoKnewTooMuch, 2));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        //the current movie rental
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        "Amount owed is 4.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(theManWhoKnewTooMuch, 4));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        //the current movie rental
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        //third movie rental
                        "\tThe Man Who Knew Too Much\t5.0\n" +
                        "Amount owed is 9.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 5 frequent renter points",
                janeDoe.statement());
    }

    @Test
    public void testStatementChildrensMovieOnly() {
        // childrens' movies cost $1.50 for the first 3 days, and $1.25/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer johnDoeJr = new Customer("Johnny Doe, Jr.");
        johnDoeJr.addMovieRental(new MovieRental(mulan, 1));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        "\tMulan\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points",
                johnDoeJr.statement());

        johnDoeJr.addMovieRental(new MovieRental(mulan, 2));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        //the first movie rental above
                        "\tMulan\t1.5\n" +
                        //the current movie rental
                        "\tMulan\t1.5\n" +
                        "Amount owed is 3.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                johnDoeJr.statement());

        johnDoeJr.addMovieRental(new MovieRental(mulan, 4));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        //the first movie rental above
                        "\tMulan\t1.5\n" +
                        //the current movie rental
                        "\tMulan\t1.5\n" +
                        //third movie rental
                        "\tMulan\t2.75\n" +
                        "Amount owed is 5.75\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 5 frequent renter points",
                johnDoeJr.statement());
    }

    @Test
    public void testStatementNewReleaseOnly() {
        // new releases cost $3.00/day
        // a rental earns 1 frequent-renter point 1 day; 2 points for 2 or more days
        Customer janeDoe = new Customer("Jane Doe");
        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 1));
        assertEquals("Rental Record for Jane Doe\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 2));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        //the current movie rental
                        "\tSlumdog Millionaire\t6.0\n" +
                        "Amount owed is 9.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                janeDoe.statement());

        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 4));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above
                        "\tSlumdog Millionaire\t3.0\n" +
                        //the current movie rental
                        "\tSlumdog Millionaire\t6.0\n" +
                        //third movie rental
                        "\tSlumdog Millionaire\t12.0\n" +
                        "Amount owed is 21.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 5 frequent renter points",
                janeDoe.statement());
    }
}