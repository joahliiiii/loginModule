package cn.joah.login.commons.captche;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class Captche {
    // 先设置图片的宽,高
    private int height=35;
    private int width=75;
    private String photoFormat="JPEG";
    public StringBuffer stringBuffer=new StringBuffer();
    // 设置文字字符串的集合
    private String codes="123456789QWERTYUPLJHGFDSAZBNM";
    // 设置字体集合
    private String[] fonts={"Algerian","宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑"};

    // 设置随机数的
    private Random rdm=new Random();

    // 随机一个字符
    public char randomChar(){
        return codes.charAt(rdm.nextInt(codes.length()));
    }
    // 随机一个颜色
    public Color randomColor(){
        int[] RGB=randomRGBs();
        return new Color(RGB[0],RGB[1],RGB[2]);
    }
    // 随机生成rgb 3个颜色值
    public int[] randomRGBs(){
        int[] RGB=new int[3];
        for (int i = 0; i < 3; i++) {
            RGB[i]=rdm.nextInt(255);
        }
        return RGB;
    }
    // 根据随机的RGB数值,得出一个int的RGB色号
    public   int randomIntColor() {
        int[] rgb = randomRGBs();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    /**
     * 随机生成字体
     * @return 随机的字体对象
     */
    public Font randomFont(){
        // 得到字体的名字
        String fontName=fonts[rdm.nextInt(fonts.length)];
        // 得到字体的style
        int style=rdm.nextInt(3);
        // 在这里最好计算一下字体的最大最小的范围在生成
        int size=rdm.nextInt(15)+20;
        return new Font(fontName,style,size);
    }
    // 画随机的干扰线
    public void randomLine(BufferedImage bufferedImage, int lineCount){
        Graphics2D graphics2D= (Graphics2D) bufferedImage.getGraphics();
        for (int i = 0; i < lineCount; i++) {
            // 生成两个点的坐标
            int x1=rdm.nextInt(width);
            int y1=rdm.nextInt(height);
            int x2=rdm.nextInt(width);
            int y2=rdm.nextInt(height);
            // stroke : 行程,笔
            // BasicStroke(),设置线段的基本信息
            graphics2D.setStroke(new BasicStroke(1.2F));
            // 随机线段的颜色
            graphics2D.setColor(randomColor());
            graphics2D.drawLine(x1,y1,x2,y2);
        }
    }
    // 随机噪点
    public void randomYawnPoint(BufferedImage bufferedImage, float yawnRate){
       /* // 设置噪点率
        float rate=0.1F;*/
        // 设置噪点个数
        int count= (int) (width*height*yawnRate);
        for (int i = 0; i < count; i++) {
            // 随机坐标
            int x=rdm.nextInt(width);
            int y=rdm.nextInt(height);
            //bufferedImage.getGraphics().setColor(randomColor());
            // 这里的setRGB() 的作用是设置某个点的RGB属性值
            bufferedImage.setRGB(x,y,randomIntColor());
        }
    }
    // 得到图片
    public BufferedImage getImage(int charCount, int linCount, float yawnRate){
        // 得到图片缓冲区
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D= (Graphics2D) bufferedImage.getGraphics();
        // 设置背景颜色
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(0,0,width-1,height-1);

        for (int i = 0; i < charCount; i++) {
            String txt=String.valueOf(randomChar());
            stringBuffer.append(txt);
            Color color=randomColor();
            Font font=randomFont();
            // 设置字体的颜色
            graphics2D.setColor(color);
            graphics2D.setFont(font);
            // 画到文件内去
            // 在这里 按个字符变为String 的方法: String.valueOf()
            // 在这里他的坐标 左上角是(0,0)
            // 这里的文字的大小不太好调
            graphics2D.drawString(txt,(width/charCount)*i+5.0F,30F);
            // 在这里已经把文字写入图片缓冲区了,不需要写出,只要在最后写出一次就好了.
//            ImageIO.write(bufferedImage,photoFormat,new FileOutputStream("/home/joah/图片/mycode.jpg"));
        }
        randomLine(bufferedImage,linCount);
        randomYawnPoint(bufferedImage,yawnRate);
//        ImageIO.write(bufferedImage,photoFormat,new FileOutputStream("/home/joah/图片/mycode.jpg"));
        return  bufferedImage;
    }
    // 输出图像到文件
    // 这里不可以设置为FileoutputStream
    public void out(BufferedImage bufferedImage, OutputStream outputStream) throws IOException {
        ImageIO.write(bufferedImage,photoFormat,outputStream);
    }
    //
    /*@Test
    public void test() throws FileNotFoundException {
        BufferedImage bufferedImage=getImage(4,6,0.07F);
        String path="/home/joah/图片/mycode.jpg";
        try {
            System.out.println("verfify code: "+stringBuffer.toString());
            outFile(bufferedImage,new FileOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
