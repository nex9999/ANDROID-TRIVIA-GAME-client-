package es.unavarra.tlm.dscr_10.Juego;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import es.unavarra.tlm.dscr_10.R;
import info.hoang8f.widget.FButton;

/**
 * Created by root on 24/11/15.
 */
public class Jugar extends Activity {
    static int ancho, alto,i = 0,posicion, posicion_otro, turno;
    Integer[][] coordenadas_casillas;
    static GameInfoResponse infoMessage;
    static boolean tiraotravez;
    static RelativeLayout quesitos_usuario1,quesitos_usuario2,dibujo, pantalla;
    static FButton  izquierda, derecha;
    String casilla;
    static Boolean estuturno;
    SharedPreferences sharedpreferences;
    static TextView  resultadodado, nombre_usuario1, nombre_usuario2, color_usuario1,color_usuario2;
    static Activity c;
    Drawable quesito_azul, quesito_verde, quesito_amarillo, quesito_marron, quesito_rojo, quesito_naranja;
    private ImageView botondado;   //Modificado nestor

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        final Context context = this.findViewById(android.R.id.content).getContext();

        infoMessage = new GameInfoResponse();
        infoMessage = InfoResponseHandler.message;

        dibujo = (RelativeLayout) findViewById(R.id.dibujo);
        pantalla = (RelativeLayout)findViewById(R.id.pantalla);

        final Lienzo fondo = new Lienzo(this);
        dibujo.addView(fondo);


        resultadodado = (TextView)findViewById(R.id.resultadodado);
        nombre_usuario1= (TextView)findViewById(R.id.usuario1_nombre);
        nombre_usuario2 = (TextView)findViewById(R.id.usuario2_nombre);
        c = this;

        botondado = (ImageView)findViewById(R.id.botondado);
        izquierda = (FButton)findViewById(R.id.izquierda);
        derecha = (FButton)findViewById(R.id.derecha);

        botondado.setImageResource(es.unavarra.tlm.dscr_10.R.drawable.dice3droll);
        izquierda.setBackgroundDrawable(getResources().getDrawable(R.drawable.dados));
        derecha.setBackgroundDrawable(getResources().getDrawable(R.drawable.dados));

        izquierda.setOnClickListener(new ListenerMove(this, 0));
        derecha.setOnClickListener(new ListenerMove(this, 1));
        botondado.setOnClickListener(new ListenerDado(this));

        //derecha.setButtonColor(getResources().getColor(R.color.fbutton_color_concrete));
        derecha.setShadowColor(getResources().getColor(R.color.fbutton_color_midnight_blue));
        derecha.setShadowEnabled(true);
        derecha.setShadowHeight(15);
        derecha.setCornerRadius(15);

        izquierda.setShadowColor(getResources().getColor(R.color.fbutton_color_midnight_blue));
        izquierda.setShadowEnabled(true);
        izquierda.setShadowHeight(15);
        izquierda.setCornerRadius(15);

        Drawable drawable = c.getResources().getDrawable(R.drawable.dice);
        drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.5),
                (int) (drawable.getIntrinsicHeight() * 0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

        derecha.setCompoundDrawables(sd.getDrawable(), null, null, null);


        Drawable drawableder = c.getResources().getDrawable(R.drawable.dice);
        drawableder.setBounds(0, 0, (int)(drawableder.getIntrinsicWidth()*0.5),
                (int)(drawableder.getIntrinsicHeight()*0.5));
        ScaleDrawable sdder = new ScaleDrawable(drawableder, 0, 32, 32);

        izquierda.setCompoundDrawables(sdder.getDrawable(), null, null, null);

        comprobarturno();


    }

    public static void dadotirado(){
        //Toast.makeText(c, String.valueOf(DiceResponseHandler.message.getShift()), Toast.LENGTH_LONG).show();
        resultadodado.setText(String.valueOf(DiceResponseHandler.message.getShift()));
        izquierda.setVisibility(View.VISIBLE);
        derecha.setVisibility(View.VISIBLE);
        if(DiceResponseHandler.message.getRight().getType().equals("hq")){
            Drawable drawable = c.getResources().getDrawable(R.drawable.cheese);
            drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                    (int)(drawable.getIntrinsicHeight()*0.5));
            ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

            izquierda.setCompoundDrawables(sd.getDrawable(), null, null, null);

        }
        if(DiceResponseHandler.message.getLeft().getType().equals("hq")){
            Drawable drawable = c.getResources().getDrawable(R.drawable.cheese);
            drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                    (int)(drawable.getIntrinsicHeight()*0.5));
            ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

            derecha.setCompoundDrawables(sd.getDrawable(), null, null, null);
        }
        if(DiceResponseHandler.message.getLeft().getType().equals("dice")){
            Drawable drawable = c.getResources().getDrawable(R.drawable.dice);
            drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                    (int)(drawable.getIntrinsicHeight()*0.5));
            ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

            derecha.setCompoundDrawables(sd.getDrawable(), null, null, null);
        }else{
            if(!DiceResponseHandler.message.getLeft().getType().equals("hq")) {
                Drawable drawable = c.getResources().getDrawable(R.drawable.right);
                drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.5),
                        (int) (drawable.getIntrinsicHeight() * 0.5));
                ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

                derecha.setCompoundDrawables(sd.getDrawable(), null, null, null);
            }
            String color_derecha = DiceResponseHandler.message.getLeft().getCategory();
            if(color_derecha.equals("blue")){
                //derecha.setBackgroundColor(Color.BLUE);
                derecha.setButtonColor(c.getResources().getColor(R.color.fbutton_color_belize_hole));
            }
            if(color_derecha.equals("brown")){
                //derecha.setBackgroundColor(Color.rgb(150,50,0));
                derecha.setButtonColor(Color.rgb(150, 50, 0));
            }
            if(color_derecha.equals("red")){
                //derecha.setBackgroundColor(Color.RED);
                derecha.setButtonColor(c.getResources().getColor(R.color.fbutton_color_pomegranate));
            }
            if(color_derecha.equals("yellow")){
               // derecha.setBackgroundColor(Color.YELLOW);
                derecha.setButtonColor(Color.YELLOW);
            }
            if(color_derecha.equals("green")){
               // derecha.setBackgroundColor(Color.GREEN);
                derecha.setButtonColor(c.getResources().getColor(R.color.fbutton_color_nephritis));
            }
            if(color_derecha.equals("orange")){
                //derecha.setBackgroundColor(Color.rgb(253,154,0));
                derecha.setButtonColor(c.getResources().getColor(R.color.fbutton_color_carrot));
            }

        }

        if(DiceResponseHandler.message.getRight().getType().equals("dice")){
            Drawable drawable = c.getResources().getDrawable(R.drawable.dice);
            drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                    (int)(drawable.getIntrinsicHeight()*0.5));
            ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);

            izquierda.setCompoundDrawables(sd.getDrawable(), null, null, null);
        }else {
            if(!DiceResponseHandler.message.getRight().getType().equals("hq")) {
                Drawable drawable = c.getResources().getDrawable(R.drawable.left);//Seteamos la imagen de una flecha al botón.
                drawable.setBounds(0, 0, (int) (drawable.getIntrinsicWidth() * 0.5),
                        (int) (drawable.getIntrinsicHeight() * 0.5));
                ScaleDrawable sd = new ScaleDrawable(drawable, 0, 32, 32);
                izquierda.setCompoundDrawables(sd.getDrawable(), null, null, null);
            }
            String color_izquierda = DiceResponseHandler.message.getRight().getCategory();

            if (color_izquierda.equals("blue")) {

                //izquierda.setBackgroundColor(Color.BLUE);
                izquierda.setButtonColor(c.getResources().getColor(R.color.fbutton_color_belize_hole));
            }
            if (color_izquierda.equals("brown")) {
                //izquierda.setBackgroundColor(Color.rgb(150,50,0));
                izquierda.setButtonColor(Color.rgb(150,50,0));
            }
            if (color_izquierda.equals("red")) {
               // izquierda.setBackgroundColor(Color.RED);
                izquierda.setButtonColor(c.getResources().getColor(R.color.fbutton_color_pomegranate));
            }
            if (color_izquierda.equals("yellow")) {
                //izquierda.setBackgroundColor(Color.YELLOW);
                izquierda.setButtonColor(Color.YELLOW);
            }
            if (color_izquierda.equals("green")) {
               // izquierda.setBackgroundColor(Color.GREEN);
                izquierda.setButtonColor(c.getResources().getColor(R.color.fbutton_color_nephritis));
            }
            if (color_izquierda.equals("orange")) {
               // izquierda.setBackgroundColor(Color.rgb(253,154,0));
                izquierda.setButtonColor(c.getResources().getColor(R.color.fbutton_color_carrot));
            }
        }
    }

    public void comprobarturno(){
        turno = infoMessage.getTurn();

        String usuarioturno = infoMessage.getPlayers().get(turno).getName();
        sharedpreferences = getSharedPreferences("Config", 0);
        String nombrejugador = sharedpreferences.getString("username","");
        if (nombrejugador.equals(usuarioturno)){

            Toast.makeText(c,"Es tu turno",Toast.LENGTH_LONG).show();
            estuturno =  true;
            Integer posicion = infoMessage.getStatus().get(turno).getPosition();
            String tipo_casilla = infoMessage.getBoard().get(posicion).getType();

            if (tipo_casilla.equals("dice") || MoveResponseHandler.tiraotravez==true){
                pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.dice3droll));
                MoveResponseHandler.tiraotravez = false;

            }else{
                String color_usuario = infoMessage.getBoard().get(posicion).getCategory();
                if(color_usuario.equals("blue")){
                    pantalla.setBackgroundColor(c.getResources().getColor(R.color.fbutton_color_belize_hole));
                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_cheese));
                    }
                }
                if(color_usuario.equals("brown")){
                    pantalla.setBackgroundColor(Color.rgb(150, 50, 0));

                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.brown_cheese));
                    }
                }
                if(color_usuario.equals("red")){
                    pantalla.setBackgroundColor(c.getResources().getColor(R.color.fbutton_color_pomegranate));
                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_cheese));
                    }
                }
                if(color_usuario.equals("yellow")){
                    pantalla.setBackgroundColor(Color.YELLOW);
                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow_cheese));
                    }
                }
                if(color_usuario.equals("green")){
                    pantalla.setBackgroundColor(c.getResources().getColor(R.color.fbutton_color_nephritis));
                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_cheese));
                    }
                }
                if(color_usuario.equals("orange")){
                    pantalla.setBackgroundColor(c.getResources().getColor(R.color.fbutton_color_carrot));
                    if(tipo_casilla.equals("hq")){
                        pantalla.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_cheese));
                    }
                }

            }
            //LinearLayOut Setup
            RelativeLayout relativeLayout= (RelativeLayout)c.findViewById(R.id.pantalla);
            //ImageView Setup
            ImageView imageView = new ImageView(c);
            //setting image resource
            imageView.setImageResource(R.drawable.user);
            //setting image position
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));

            //adding view to layout
            relativeLayout.addView(imageView);


            comprobarstatus();

        }else{

            Toast.makeText(c,"Es turno de "+usuarioturno,Toast.LENGTH_LONG).show();
            botondado.setEnabled(false);
        }

    }

    public void comprobarstatus(){
        String status = infoMessage.getStatus().get(turno).getAction();
        if(status.equals("dice")){
            botondado.setEnabled(true);
        }
        if(status.equals("choose")){
            izquierda.setVisibility(View.VISIBLE);
            derecha.setVisibility(View.VISIBLE);
            botondado.setVisibility(View.GONE);
        }
        if (status.equals("response")){
            botondado.setVisibility(View.GONE);
            this.finish();
            Intent pregunta = new Intent(this, Pregunta.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);;
            startActivity(pregunta);
        }
    }

    class Lienzo extends View {
        public Lienzo(Context context) {
            super(context);
        }
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;
        protected void onDraw(Canvas canvas) {
            ancho = canvas.getWidth();
            alto = canvas.getHeight();

            String nombreUsuario1 = infoMessage.getPlayers().get(0).getName();
            nombre_usuario1.setText(nombreUsuario1);
            if (infoMessage.getStatus().get(turno).getHq().size()==6){
                Toast.makeText(c,"HAS GANADO!!",Toast.LENGTH_LONG).show();
                finish();
            }
            for (int i = 0 ; i < infoMessage.getStatus().get(0).getHq().size() ; i++){
                String quesitosUsuario1 = infoMessage.getStatus().get(0).getHq().get(i);
                if (quesitosUsuario1.equals("blue")){
                    quesito_azul = getContext().getResources().getDrawable(R.drawable.quesito_azul);
                    quesito_azul.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_azul.draw(canvas);
                }
                if (quesitosUsuario1.equals("green")){
                    quesito_verde = getContext().getResources().getDrawable(R.drawable.quesito_verde);
                    quesito_verde.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_verde.draw(canvas);
                }
                if (quesitosUsuario1.equals("brown")){
                    quesito_marron = getContext().getResources().getDrawable(R.drawable.quesito_marron);
                    quesito_marron.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_marron.draw(canvas);
                }
                if (quesitosUsuario1.equals("red")){
                    quesito_rojo= getContext().getResources().getDrawable(R.drawable.quesito_rojo);
                    quesito_rojo.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_rojo.draw(canvas);
                }
                if (quesitosUsuario1.equals("yellow")){
                    quesito_amarillo = getContext().getResources().getDrawable(R.drawable.quesito_amarillo);
                    quesito_amarillo.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_amarillo.draw(canvas);
                }
                if (quesitosUsuario1.equals("orange")){
                    quesito_naranja = getContext().getResources().getDrawable(R.drawable.quesito_naranja);
                    quesito_naranja.setBounds(screenWidth/4,0,screenWidth/4+50,50);
                    quesito_naranja.draw(canvas);
                }
            }



            String nombreUsuario2 = infoMessage.getPlayers().get(1).getName();
            nombre_usuario2.setText(nombreUsuario2);
            for (int i = 0 ; i < infoMessage.getStatus().get(1).getHq().size() ; i++){
                String quesitosUsuario2 = infoMessage.getStatus().get(1).getHq().get(i);
                if (quesitosUsuario2.equals("blue")){
                    quesito_azul = getContext().getResources().getDrawable(R.drawable.quesito_azul);
                    quesito_azul.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_azul.draw(canvas);
                }
                if (quesitosUsuario2.equals("green")){
                    quesito_verde = getContext().getResources().getDrawable(R.drawable.quesito_verde);
                    quesito_verde.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_verde.draw(canvas);
                }
                if (quesitosUsuario2.equals("brown")){
                    quesito_marron = getContext().getResources().getDrawable(R.drawable.quesito_marron);
                    quesito_marron.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_marron.draw(canvas);
                }
                if (quesitosUsuario2.equals("red")){
                    quesito_rojo = getContext().getResources().getDrawable(R.drawable.quesito_rojo);
                    quesito_rojo.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_rojo.draw(canvas);
                }
                if (quesitosUsuario2.equals("yellow")){
                    quesito_amarillo = getContext().getResources().getDrawable(R.drawable.quesito_amarillo);
                    quesito_amarillo.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_amarillo.draw(canvas);
                }
                if (quesitosUsuario2.equals("orange")){
                    quesito_naranja = getContext().getResources().getDrawable(R.drawable.quesito_naranja);
                    quesito_naranja.setBounds(screenWidth/4,50,screenWidth/4+50,100);
                    quesito_naranja.draw(canvas);
                }
            }

            //#########################################################################################################
            //________________________________INTENTO DIBUJAR EL TABLERO_______________________________________________
            //#########################################################################################################
            Paint myPaint = new Paint();



            int j= 0;
            int ladoCuadrado =(int) (0.04*screenWidth);
            int x = (int)(metrics.widthPixels/2 -ladoCuadrado*11/2);
            int y = (int)(metrics.heightPixels*0.3);
            System.out.println("__"+infoMessage.getStatus().get(turno).getPosition());
            for(int i = 0; i < infoMessage.getBoard().size()/4 ; i++){

                int color=vercolor(i);
                myPaint.setColor(color);

                canvas.drawRect(x,y,x+ladoCuadrado,y+ladoCuadrado,myPaint);

                if(infoMessage.getBoard().get(i).getType().equals("hq")){

                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getBoard().get(i).getType().equals("dice")){
                    System.out.println("__Dibuja dados");
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.dice3d);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getStatus().get(turno).getPosition()==i){
                    System.out.println("__Dibuja usuario");
                    //canvas.drawRect(x + 5, y + 5, x + ladoCuadrado / 2, y + ladoCuadrado / 2, myPaint);
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.user);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x,y, myPaint);
                }
                x = x+ladoCuadrado;
                j = i;

            }
            for(i = j+1; i < infoMessage.getBoard().size()/2 ; i++){
                int color=vercolor(i);
                myPaint.setColor(color);

                canvas.drawRect(x,y,x+ladoCuadrado,y+ladoCuadrado,myPaint);

                if(infoMessage.getBoard().get(i).getType().equals("hq")){
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getBoard().get(i).getType().equals("dice")){

                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.dice3d);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getStatus().get(turno).getPosition()==i){
                    System.out.println("__Dibuja usuario");
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.user);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                y = y+ladoCuadrado;
                j = i;

            }
            for(i = j+1; i < 3*infoMessage.getBoard().size()/4 ; i++){
                int color=vercolor(i);
                myPaint.setColor(color);

                canvas.drawRect(x,y,x+ladoCuadrado,y+ladoCuadrado,myPaint);

                if(infoMessage.getBoard().get(i).getType().equals("hq")){
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getBoard().get(i).getType().equals("dice")){

                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.dice3d);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getStatus().get(turno).getPosition()==i){
                    System.out.println("__Dibuja usuario");
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.user);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                x = x-ladoCuadrado;
                j = i;

            }
            for(i = j+1 ; i < infoMessage.getBoard().size() ; i++){
                int color=vercolor(i);
                myPaint.setColor(color);

                canvas.drawRect(x,y,x+ladoCuadrado,y+ladoCuadrado,myPaint);

                if(infoMessage.getBoard().get(i).getType().equals("hq")){
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.cheese);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getBoard().get(i).getType().equals("dice")){

                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.dice3d);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                if(infoMessage.getStatus().get(turno).getPosition()==i){
                    System.out.println("__Dibuja usuario");
                    Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.user);
                    b = Bitmap.createScaledBitmap(b,ladoCuadrado,ladoCuadrado,true);
                    canvas.drawBitmap(b, x, y, myPaint);
                }
                y = y-ladoCuadrado;

            }
        }


    }

    public void acabar(){
        this.finish();
    }

    public int vercolor(int position){
        String tipo = infoMessage.getBoard().get(position).getType();
        String color_string;
        int color=0;
        if(infoMessage.getBoard().get(position).getType().equals("dice")){
            color_string="dados";
        }else{
            color_string=infoMessage.getBoard().get(position).getCategory();
        }

        switch(color_string){
            case "dados":
                color= Color.rgb(255,255,255);
                break;
            case "orange":
                color = getResources().getColor(R.color.fbutton_color_carrot);
                break;
            case "blue":
                color = getResources().getColor(R.color.fbutton_color_belize_hole);
                break;
            case "red":
                color = getResources().getColor(R.color.fbutton_color_pomegranate);
                break;
            case "brown":
                color = Color.rgb(153,76,0);
                break;
            case "yellow":
                color = Color.rgb(255,255,0);
                break;
            case "green":
                color = getResources().getColor(R.color.fbutton_color_nephritis);
                break;
        }

        return color;
    }
}
