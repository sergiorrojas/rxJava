package news;

public class NewsItem {

    private int id;
    private String content;
    private NewsType newsType;

    public NewsItem(int id, String content, NewsType newsType) {
        this.id = id;
        this.content = content;
        this.newsType = newsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

}
