import OpcionB2.NewCLI;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.Stack;
import java.util.function.BiFunction;

public class TestNewCLI {
        @Test
        public void testGetObservable(){
            NewCLI newCLI = new NewCLI();


            TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

            newCLI.getObservable(1).subscribe(testSubscriber);

            testSubscriber.assertCompleted();
            testSubscriber.assertNoErrors();
            testSubscriber.assertValue(1);

        }

        @Test
        public void testGetSumaObservable(){
            NewCLI n = new NewCLI();

            TestSubscriber<BiFunction> testSubscriber = new TestSubscriber<>();

            n.getSumaObservable().subscribe(testSubscriber);
            testSubscriber.assertCompleted();
            testSubscriber.assertNoErrors();

        }

    @Test
    public void testGetRestaObservable(){
        NewCLI n = new NewCLI();

        TestSubscriber<BiFunction> testSubscriber = new TestSubscriber<>();

        n.getRestaObservable().subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();

    }

    @Test
    public void testGetMulObservable(){
        NewCLI n = new NewCLI();

        TestSubscriber<BiFunction> testSubscriber = new TestSubscriber<>();

        n.getMulObservable().subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testGetZipObservable(){
        NewCLI n = new NewCLI();
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        n.getResult(n.getSumaObservable(), n.getObservable(1), n.getObservable(2)).subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
    }

    @Test
    public void testCalculos(){
        NewCLI n = new NewCLI();
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Stack<Observable<BiFunction<Integer, Integer, Integer>>> funcStack = new Stack<>();
        Stack<Observable<Integer>> intStack = new Stack<>();

        funcStack.push(n.getSumaObservable());
        intStack.push(n.getObservable(3));
        funcStack.push(n.getRestaObservable());
        intStack.push(n.getObservable(5));
        intStack.push(n.getObservable(2));


        n.getResult(funcStack.pop(), intStack.pop(), n.getResult(funcStack.pop(), intStack.pop(), intStack.pop())).subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(6);

    }

    @Test
    public void testCalculoMult(){
        NewCLI n = new NewCLI();
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

        Stack<Observable<BiFunction<Integer, Integer, Integer>>> funcStack = new Stack<>();
        Stack<Observable<Integer>> intStack = new Stack<>();

        funcStack.push(n.getRestaObservable());
        intStack.push(n.getObservable(2));
        intStack.push(n.getObservable(2));
        funcStack.push(n.getSumaObservable());
        intStack.push(n.getObservable(3));
        funcStack.push(n.getMulObservable());
        intStack.push(n.getObservable(3));

        while(!funcStack.isEmpty())
            intStack.push(n.getResult(funcStack.pop(), intStack.pop(), intStack.pop()));

        intStack.pop().subscribe(testSubscriber);
        //n.getResult(funcStack.pop(), intStack.pop(), n.getResult(funcStack.pop(), intStack.pop(), intStack.pop())).subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(13);



    }


}
