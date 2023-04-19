package rainbow.model.utils;

public abstract class AbstractFileReader implements FileReader {
    protected String filename;

    public AbstractFileReader(String filename){
        this.filename = filename;
    }

    public abstract String readFile();
    public abstract void writeFile(String content);
}
