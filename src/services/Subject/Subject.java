package services.Subject;

import Model.Book;
import Model.Member;
import services.Observers.Observer;

import java.util.List;

public interface Subject {
     void addObserver(Observer observer);
     void removeObserver(Observer observer);
     void notifyObservers(Object object);
}
