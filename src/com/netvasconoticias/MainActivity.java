package com.netvasconoticias;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<ItemListView> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o layout onde contem o ListView
        setContentView(R.layout.main);

        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.tela_consulta_listView);
        //Define o Listener quando alguem clicar no item.
        listView.setOnItemClickListener(this);

        createListView();
    }

    private void createListView() {
        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemListView>();
        //Get the xml string from the server
        String xml = XmlUtils.getXML("http://feeds.feedburner.com/Netvasco-Noticias");
        Document doc = XmlUtils.XMLfromString(xml);

        NodeList nodes = doc.getElementsByTagName("item");
        /*ItemListView item1 = new ItemListView("Guilherme Biff", R.drawable.biff);
        ItemListView item2 = new ItemListView("Lucas Volgarini", R.drawable.volgarini);
        ItemListView item3 = new ItemListView("Eduardo Ricoldi", R.drawable.ricoldi);
        ItemListView item4 = new ItemListView("Felipe Panngo", R.drawable.panngo);*/
		for (int i = 0; i < nodes.getLength(); i++) {
			Element e = (Element)nodes.item(i);
			ItemListView item = new ItemListView( XmlUtils.getValue(e, "title"), XmlUtils.getValue(e, "description"),R.drawable.ico_vasco_hdpi);
			itens.add(item);

		}
        /*itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);*/

        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);

        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        //Pega o item que foi selecionado.
        ItemListView item = adapterListView.getItem(arg2);
        
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	//EditText editText = (EditText) findViewById(R.id.);
    	String message = item.getNoticia().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent); 
        
        //Demostração
        //Toast.makeText(this, "Você Clicou em: " + item.getNoticia(), Toast.LENGTH_LONG).show();
    }
    
    
}
