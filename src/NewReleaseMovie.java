public class NewReleaseMovie extends Movie {

    public static final int FREQUENT_RENTER_POINTS = 1;
    public static final int DAY_RENT_NUM = 3;
    public static final int BASE_NUM = 0;

    public NewReleaseMovie(String title) {
        super(title);
    }

    @Override
    public double getCharge(int _daysRented) {
        double result = BASE_NUM;
        // determine amounts for each line
        result += _daysRented * DAY_RENT_NUM;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        int frequentRenterPoints = FREQUENT_RENTER_POINTS;
        // add bonus for a two day new release rental
        if ( _daysRented > 1)
            frequentRenterPoints += frequentRenterPoints;
        return frequentRenterPoints;
    }
}
