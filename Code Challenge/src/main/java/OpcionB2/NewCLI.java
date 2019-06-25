package OpcionB2;

import rx.Observable;

import java.util.function.BiFunction;
import java.util.function.Function;

import static rx.Observable.fromCallable;

public class NewCLI {
    public Observable<Integer> getObservable(Integer integer){
        return Observable.fromCallable(() -> integer);
    }

    public Observable<BiFunction<Integer, Integer, Integer>> getSumaObservable()
    {
        return Observable.fromCallable(() -> (x,y) -> x+y);
    }

    public Observable<BiFunction<Integer, Integer, Integer>> getRestaObservable()
    {
        return Observable.fromCallable(() -> (x,y) -> y-x);
    }

    public Observable<BiFunction<Integer, Integer, Integer>> getMulObservable()
    {
        return Observable.fromCallable(() -> (x,y) -> x * y);
    }

    public Observable<Integer> getResult(Observable<BiFunction<Integer, Integer, Integer>> functionObservable,
                                         Observable<Integer> integerObservable1,
                                         Observable<Integer> integerObservable2){

        return Observable.zip(integerObservable1, integerObservable2, functionObservable, (x, y, z) -> (Integer)z.apply(x, y));

    }

}
