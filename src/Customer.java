import java.util.*;

class Customer {

    public String _name;
    private Vector<MovieRental> _movieRentals = new Vector<MovieRental>();
    private Vector<VideoGameRental> _videoGameRental = new Vector<VideoGameRental>(); 
    public Customer(String name) {
        _name = name;
    }

    public void addMovieRental(MovieRental arg) {
        _movieRentals.addElement(arg);
    }

    public void addVideoGameRental(VideoGameRental arg) {
        _videoGameRental.addElement(arg);
    }
    
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration<MovieRental> movieRentals = _movieRentals.elements();
        Enumeration<VideoGameRental> videoGameRentals = _videoGameRental.elements();
        String result = "Rental Record for " + _name + "\n";

        while (movieRentals.hasMoreElements()) {
            MovieRental each = (MovieRental) movieRentals.nextElement();

            // add frequent renter points
            frequentRenterPoints += getFrequentRenterPoints(each);


            // show figures for this rental
            result += "\t" + each.getMovie()._title+ "\t"
                    + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }
        while (videoGameRentals.hasMoreElements()) {
            VideoGameRental aRental = (VideoGameRental) videoGameRentals.nextElement();
            frequentRenterPoints += aRental.getFrequentRenterPoints();
            result += "\t" + aRental.getVideoGame() + "\t"
            		+ String.valueOf(aRental.getCharge()) + "\n";
            totalAmount += aRental.getCharge();
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints)
                + " frequent renter points";
        return result;
    }

    private int getFrequentRenterPoints(MovieRental each) {
        int frequentRenterPoints = 1;
        // add bonus for a two day new release rental
        if (each.getDaysRented() > 1) frequentRenterPoints++;
        return frequentRenterPoints;
    }

}
