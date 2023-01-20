package sh4k4w4t.github.io.roomdatabasetest.adapter.writer;

import sh4k4w4t.github.io.roomdatabasetest.model.Writer;

public class WriterDataController {

    public static WriterDataController INSTANCE;
    public static WriterDataController getInstance() {
        if (INSTANCE == null) {
            INSTANCE= new WriterDataController();
        }
        return INSTANCE;
    }

    WriterFragmentInterface writerFragmentInterface;
    Writer currentWriter;

    public WriterFragmentInterface getWriterFragmentInterface() {
        return writerFragmentInterface;
    }

    public void setWriterFragmentInterface(WriterFragmentInterface writerFragmentInterface) {
        this.writerFragmentInterface = writerFragmentInterface;
    }

    public Writer getCurrentWriter() {
        return currentWriter;
    }

    public void setCurrentWriter(Writer currentWriter) {
        this.currentWriter = currentWriter;
    }
}
