public abstract class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    public String _title;

    public Movie(String title) {
        _title = title;
    }

    public abstract double getCharge(int _daysRented);

    public abstract int getFrequentRenterPoints(int _daysRented);
}

