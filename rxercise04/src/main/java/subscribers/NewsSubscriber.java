package subscribers;

import com.sun.javafx.UnmodifiableArrayList;
import news.NewsItem;
import news.NewsType;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.List;

import static news.NewsType.ESPECTACULOS;
import static news.NewsType.POLITICA;

public class NewsSubscriber extends Subscriber<NewsItem> {
    private List<NewsType> newsTypes;
    private String subscriberName;

    public NewsSubscriber(List<NewsType> newsTypes){
        this.newsTypes = newsTypes;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i<newsTypes.size(); i++){
            stringBuilder.append(newsTypes.get(i).toString());
            if(i!=newsTypes.size()-1)
                stringBuilder.append("|");
        }
        this.subscriberName = stringBuilder.toString();
    }

    public NewsSubscriber(NewsType newsType){
        this.subscriberName = newsType.toString();
        this.newsTypes = new ArrayList<NewsType>();
        this.newsTypes.add(newsType);
    }

    @Override
    public void onCompleted() {
        System.out.println("se acabo");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error:" + throwable.getMessage());
    }

    @Override
    public void onNext(NewsItem item) {
        if (newsTypes.size() == 1 && newsTypes.contains(POLITICA)){
            if(ESPECTACULOS.equals(item.getNewsType())){
                System.out.println("ESPECTACULOS en POLITICA terminando subscripcion");
                this.unsubscribe();
            }
        }
        if (newsTypes.contains(item.getNewsType())) {
            System.out.println("Subscriptor " + subscriberName + " - " + item.getContent());
        }
    }

}

