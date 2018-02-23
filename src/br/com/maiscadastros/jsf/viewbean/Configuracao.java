package br.com.maiscadastros.jsf.viewbean;

import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "ConfiguracaoVB")
public class Configuracao
{
    private Map<String, String> temas;
    private String tema;

    public String getTema()
    {
        return tema;
    }

    public void setTema(String pTema)
    {
        tema = pTema;
    }

    public Map<String, String> getTemas()
    {
        return temas;
    }

    public void setThemes(Map<String, String> pTemas)
    {
        temas = pTemas;
    }

    @PostConstruct
    public void init() {
        setTema("cupertino");

        temas = new TreeMap<String, String>();
        temas.put("Aristo", "aristo");
        temas.put("Black-Tie", "black-tie");
        temas.put("Blitzer", "blitzer");
        temas.put("Bluesky", "bluesky");
        temas.put("Bootstrap", "bootstrap");
        temas.put("Casablanca", "casablanca");
        temas.put("Cupertino", "cupertino");
        temas.put("Dark-Hive", "dark-hive");
        temas.put("Dot-Luv", "dot-luv");
        temas.put("Eggplant", "eggplant");
        temas.put("Excite-Bike", "excite-bike");
        temas.put("Flick", "flick");
        temas.put("Glass-X", "glass-x");
        temas.put("Hot-Sneaks", "hot-sneaks");
        temas.put("Humanity", "humanity");
        temas.put("Le-Frog", "le-frog");
        temas.put("Midnight", "midnight");
        temas.put("Mint-Choc", "mint-choc");
        temas.put("Overcast", "overcast");
        temas.put("Pepper-Grinder", "pepper-grinder");
        temas.put("Redmond", "redmond");
        temas.put("Rocket", "rocket");
        temas.put("Sam", "sam");
        temas.put("Smoothness", "smoothness");
        temas.put("South-Street", "south-street");
        temas.put("Start", "start");
        temas.put("Sunny", "sunny");
        temas.put("Swanky-Purse", "swanky-purse");
        temas.put("Trontastic", "trontastic");
        temas.put("UI-Darkness", "ui-darkness");
        temas.put("UI-Lightness", "ui-lightness");
        temas.put("Vader", "vader");
    }

    public void salvarTema()
    {
    }
}
