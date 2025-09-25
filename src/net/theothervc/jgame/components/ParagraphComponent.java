package net.theothervc.jgame.components;

import net.theothervc.jgame.util.AspectRatio;
import net.theothervc.jgame.util.CharOps;

import java.awt.*;
import java.util.ArrayList;

public class ParagraphComponent extends Component {

    //TODO: Padding

    //Variables
    public static final int JUSTIFICATION_LEFT = 0;
    public static final int JUSTIFICATION_RIGHT = 1;
    public static final int JUSTIFICATION_CENTER = 2;
    public int Justification = 0;
    public int paddingLeft = 0;
    public int paddingRight = 0;
    public int paddingUp = 0;
    public int paddingDown = 0;
    String text;
    Color textColor = Color.BLACK;
    private AspectRatio fontRatio;
    private float fontSize = 0;
    public Font font;
    float preferredSize;
    private Dimension  oldSize;
    private checkMethodInterface checkMethod;

    //Constructor. Pretty Self Explanatory.

    public ParagraphComponent(int x, int y, int w, int h, String text, Font font) {
        super(x, y, w, h);
        this.text = text;
        this.font = font;
        this.preferredSize = font.getSize();
        this.shouldRender = false;
    }

    //checkMethod Is Used Later On.

    public ParagraphComponent(int x, int y, int w, int h, String text, Font font, checkMethodInterface func) {
        this(x,y,w,h,text,font);
        checkMethod = func;
    }

    //More Self Explanatory Constructors.

    public ParagraphComponent(int x, int y, int w, int h, String text, Font font, int justification) {
        this(x,y,w,h,text,font);
        Justification = justification;
    }

    public ParagraphComponent(int x, int y, int w, int h, String text, Font font, checkMethodInterface func, int justification) {
        this(x,y,w,h,text,font,func);
        Justification = justification;
    }

    //By Setting The Font Ratio To Null And Font Size To Zero We Queue Them To Be Recalculated At The Next Draw, Which Should Be In The Same Frame.

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        this.fontRatio = null;
        this.fontSize = 0;
    }

    //Font Style And His Cousins - The Overloads.


    //Font Overload
    public void setFontStyle(Font font) {
        this.font = font;
        this.preferredSize = font.getSize();
        this.fontRatio = null;
        this.fontSize = 0;
    }

    //Size Overload
    public void setFontStyle(float size) {
        this.preferredSize = size;
        this.fontRatio = null;
        this.fontSize = 0;
    }

    //Color Overload
    public void setFontStyle(Color color) {
       textColor = color;
    }

    public int getPaddedX() {
      int X = super.getX();
      return (int) (X+screenSize.width*((float) paddingLeft/1000));
    }

    public int getPaddedY() {
        int Y = super.getY();
        return (int) (Y+screenSize.width*((float) paddingUp/1000));
    }

    public int getPaddedWidth() {
        int width = super.getWidth();
        int paddedWidth = (int) ((width-screenSize.width*((float) paddingRight/1000))-screenSize.width*((float) paddingLeft/1000));
        if (paddedWidth > 34) return paddedWidth; else throw new RuntimeException("Width Of Component Is Zero Or Less.");
    }

    public int getPaddedHeight() {
        int height = super.getHeight();
        int paddedHeight = (int) ((height-screenSize.width*((float) paddingDown/1000))- screenSize.width*((float) paddingUp/1000));
        if (paddedHeight > 34) return paddedHeight; else throw new RuntimeException("Height Of Component Is Zero Or Less.");
    }

    public Dimension getPaddedSize() {
        return new Dimension(getPaddedWidth(),getPaddedHeight());
    }

    //Before You Move Down, A Warning:
    //The Following Code Was Typed With Pure Hate And Utter Exhaustion. It
    //Is An Abomination To The Concept Of 'Programming'.



    //Without Further Ado, Feast Your Eyes On The Bubblegum And Fuckassery That Holds This Component Together.


    //The Wrap And Scale Functions.










//* Here We Go
    private ArrayList<String> wrap(FontMetrics metrics) {
        //Create Result List, Split Text By Line Breaks
        ArrayList<String> result = new ArrayList<>();
        String[] parts = text.split("(\r\n|\r|\n|\\$N)");

        //Looping Through, Split The Lines By Spaces To Get The Words

        //* You Feel Like Your Going To Have A Bad Time
        for (String part : parts) {
            String[] words = part.split(" ");
            ArrayList<String> line = new ArrayList<>();

            //Loop Through The Words And Keep Adding Them Until The Size Of The Line Exceeds The Width Of The Component.
            //Yes FontMetrics.stringWidth() Is Inaccurate And Yes I Fixed It With Manual Padding
            //And I Know Having The Same Regex Multiple Times Is Bad For Performance But At This Point I'm Done With This File.
            for (int e = 0; e < words.length; e++) {
                String word = words[e];
                if (metrics.stringWidth(String.join(" ", line).replaceAll("(?<!\\\\)\\$.|\\\\(?=\\$.)","") + word.replaceAll("(?<!\\\\)\\$.|\\\\(?=\\$.)",""))+getPaddedWidth()/20 < getPaddedWidth()) {
                    line.add(word);

                    //If The Line Is Empty (AKA Has One Word), Then It Means One Word Is Longer Than A Line And We Can Switch To Our Char Wrapping Code,
                    //AKA The Exact Same Code With Swapped Variables
                } else if (line.isEmpty()) {
                    //Loop Through The Characters In The Word ...Lorem Ipsum Dolor Sit... You Get The Idea.
                    ArrayList<Character> parsedWord = new ArrayList<>();
                    for (int i = 0; i < word.length(); i++) {
                        char letter = word.charAt(i);
                        if (metrics.stringWidth(CharOps.toStringArray(parsedWord).replaceAll("(?<!\\\\)\\$.|\\\\(?=\\$.)","") + letter) < getPaddedWidth()) {
                            parsedWord.add(letter);
                        } else {
                            //Once Word Has Been Split Into A Fitting Chunk, Add It To The Result And
                            //Replace The Word In The Original Array With The Remainder.
                            result.add(CharOps.toStringArray(parsedWord));
                            line = new ArrayList<>();
                            words[e] = word.substring(i);
                            //Set The Main Loop Back A Step Or It'll Skip The Rest Of The Word
                            e--;
                            break;
                        }
                    }
                } else {
                    //Once The Line Is Longer Than The Width, Add The Words In The Line
                    //To The Result And Reset The Line
                    result.add(String.join(" ", line));
                    line = new ArrayList<>();
                    //Again, The Word That Pushed The Line Over The Threshold Would Be Ignored If Not For This Line.
                    e--;

                }
            }
            //The Last Line Before A Line Break Is Ignored For Some Reason, We Just Add It Here And That Simulates Line Breaks Somehow??
            result.add(String.join(" ", line));
        }
        return result;
    }


    //Interlude, Text Coloring Function
    public void write(Graphics g, FontMetrics metrics,ArrayList<String> lines,ArrayList<Integer> lineXs) {
        //Take The List Of Lines And Recombine Them With $N - The Command Code For Line Break.
        //We Also Add An Extra Character To The Start And Line Break To The End Or The Last Line And First Char Wouldn't Render
        String attachedLines = " "+String.join("$N",lines)+"$N";
        //Split The Code By The Commands And Loop Through The Segments
        String[] codes = attachedLines.split("(?<!\\\\)\\$");
        int lineNum = 1;
        int lineX = lineXs.getFirst();
        for (int i = 1; i < codes.length; i++) {
            //Get The Char After The Command Sign, This Is The Command Code
            char code = codes[i].charAt(0);
            //Draw The Text From The Last Command To This One, Minus The Command Code.
            //We Also Replace Any \ In \$CODE To Nothing To Delete Them.
            //Increment The Line's X So We Keep The Cursor At The End.
            g.drawString(codes[i-1].substring(1).replaceAll("\\\\(?=\\$.)",""),lineX,getPaddedY()+metrics.getHeight()*lineNum);
            lineX += metrics.stringWidth(codes[i-1].substring(1));
            switch (code) {
                case 'N':
                    //Increment Line Number And Set The Lines X To The One Calculated Earlier In Stack, Later In This File.
                    lineX = lineXs.get(lineNum);
                    lineNum++;
                    break;

                //Not Much Explanation Needed For These.
                case 'W':
                    g.setColor(Color.WHITE);
                    break;

                case 'Y':
                    g.setColor(Color.YELLOW);
                    break;

                case 'O':
                    g.setColor(Color.ORANGE);
                    break;

                case 'G':
                    g.setColor(Color.GREEN);
                    break;

                case 'B':
                    g.setColor(Color.BLUE);
                    break;

                case 'C':
                    g.setColor(Color.CYAN);
                    break;

                case 'P':
                    g.setColor(Color.PINK);
                    break;

                case 'M':
                    g.setColor(Color.MAGENTA);
                    break;

                case 'Q':
                    g.setColor(Color.GRAY);
                    break;

                case 'A':
                    g.setColor(Color.LIGHT_GRAY);
                    break;

                case 'Z':
                    g.setColor(Color.DARK_GRAY);
                    break;

                case 'X':
                    g.setColor(Color.BLACK);
                    break;

                case 'R':
                    g.setColor(Color.RED);
            }
            //If There Is A checkMethod We Call It So The Dev Can Add Their Own Command Codes.
            if (checkMethod != null) checkMethod.checkCommand(code,g);
        }
    }






    //It's A Beautiful Day.
    //Birds Are Singing,
    //Flowers Are Blooming.
    //On Days Like This Devs Like You...



    //Should Not Have To Deal With This Bullshit.

    //The Scaling Function.
    @Override
    public void draw(Graphics g, Dimension screenSize) {
        super.draw(g, screenSize);

        //Font Ratio Is Calculated Here
        if (this.fontRatio == null) this.fontRatio = AspectRatio.fromDimension(new Dimension(getPaddedWidth(), (int) (screenSize.width * (preferredSize / 1000))));

        //Set Text Settings Before We Draw Anything
        g.setColor(textColor);
        g.setFont(font.deriveFont((float) (fontRatio.fitToDimension(new Dimension(getPaddedWidth(), getPaddedHeight())).height)));

        //Variables
        FontMetrics metrics;
        ArrayList<String> lines;
        int lineHeight;


        //Make The Font One Point Smaller And Rewrap Until It Fits The Height.
        //If It's Already Smaller, The First Three Lines Will Run But They're Required Anyway.
        //We Save The Correct Font Size So It's Not Running 300 Times A Frame.

        while (true) {
            metrics = g.getFontMetrics();
            lines = wrap(metrics);
            lineHeight = metrics.getHeight();
            if (lines.size()*lineHeight < getPaddedHeight()) break;
            g.setFont(g.getFont().deriveFont((float) g.getFont().getSize()-1));
            if (oldSize == getPaddedSize()) {
                g.setFont(g.getFont().deriveFont(fontSize));
            }
        }
        oldSize = getPaddedSize();
        fontSize = g.getFont().getSize();

        //Create The List Of Xs For Justification
        ArrayList<Integer> lineXs = new ArrayList<>();
            for (String line : lines) {
                //If 2 Then Math For Center, If One Then Math For Right, Else Left, Or Set To X
                if (Justification == 2) lineXs.add(getPaddedX() -(metrics.stringWidth(line.replaceAll("(?<!\\\\)\\$.|\\\\(?=\\$.)",""))/2)+(getPaddedWidth()/2));
                else if (Justification == 1) lineXs.add((getPaddedX() - metrics.stringWidth(line.replaceAll("(?<!\\\\)\\$.|\\\\(?=\\$.)",""))+getPaddedWidth())-getPaddedWidth()/20);
                else lineXs.add(getPaddedX());
        }
            //The Value Of This Doesn't Matter But It's Needed To Prevent ArrayOutBoundsError.
        lineXs.add(0);
        //Draw The Text With Respect To Text Commands.

        write(g,metrics,lines,lineXs);
    }
}