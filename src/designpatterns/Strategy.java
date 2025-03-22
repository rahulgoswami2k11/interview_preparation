package designpatterns;

import lombok.Data;

import javax.swing.text.html.HTML;
import java.util.List;

enum OutputFormat {
    HTML,
    MARKDOWN
}

interface ListStrategy {
    default void start(StringBuilder sb) {}
    void addItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class HtmlListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addItem(StringBuilder sb, String item) {
                sb.append("<li>")
                        .append(System.lineSeparator())
                        .append("     ")
                        .append(item)
                        .append(System.lineSeparator())
                .append("</li>")
                        .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class MarkdownListStrategy implements ListStrategy {

    @Override
    public void addItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

@Data
class TextProcessor {
    private StringBuilder sb;
    private ListStrategy strategy;

    public TextProcessor() {
        sb = new StringBuilder();
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case HTML -> strategy = new HtmlListStrategy();
            case MARKDOWN -> strategy = new MarkdownListStrategy();
        }
    }

    public void appendList(List<String> items) {
        strategy.start(sb);
        for(String item: items) {
            strategy.addItem(sb, item);
        }
        strategy.end(sb);
    }

    public void printOutput() {
        System.out.println(sb.toString());
    }

    public void clearOutput() {
        sb.setLength(0);
    }

}

public class Strategy {
    public static void main(String[] args) {
        TextProcessor processor = new TextProcessor();
        processor.setOutputFormat(OutputFormat.HTML);
        processor.appendList(List.of("inheritance", "encapsulation", "polimorphism"));
        processor.printOutput();
        processor.clearOutput();

        System.out.println();

        processor.setOutputFormat(OutputFormat.MARKDOWN);
        processor.appendList(List.of("inheritance", "encapsulation", "polimorphism"));
        processor.printOutput();
        processor.clearOutput();

    }
}
