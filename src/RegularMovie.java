public class RegularMovie extends Movie {

    public static final int BASE_DAY = 2;
    public static final double EXTRA_NUM = 1.5;
    public static final int BASE_MON = 2;
    public static final int BASE_POINT = 1;

    public RegularMovie(String title) {
        super(title);
    }

    @Override
    public double getCharge(int _daysRented) {
        double result = 0;
        // determine amounts for each line
        result += BASE_MON;
        if (_daysRented > BASE_DAY)
            result += (_daysRented - BASE_DAY) * EXTRA_NUM;
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int _daysRented) {
        int frequentRenterPoints = BASE_POINT;
        // add bonus for a two day new release rental
        if (_daysRented > 1)
            frequentRenterPoints += frequentRenterPoints;
        return frequentRenterPoints;
    }
}
