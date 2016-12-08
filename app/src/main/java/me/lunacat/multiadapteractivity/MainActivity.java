package me.lunacat.multiadapteractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.lunacat.multiadapter.MultiAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv = (RecyclerView) findViewById(R.id.activity_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<ITableCell> items = getCell();
        MultiAdapter<ITableCell> adapter = new MultiAdapter<>();
        adapter.addAll(items);

        adapter.add(TextCell.class, TextCellViewHolder.class, R.layout.item_text);
        adapter.add(ImageCell.class, ImageCellViewHolder.class, R.layout.item_image);
        rv.setAdapter(adapter);
    }

    public List<ITableCell> getCell() {
        ArrayList<ITableCell> cells = new ArrayList<>();
        cells.addAll(getCatCells());
        cells.addAll(getDogCells());
        cells.addAll(getBearCells());
        return cells;
    }

    public List<ITableCell> getCatCells() {
        ArrayList<ITableCell> cells = new ArrayList<>();
        cells.add(new TextCell(
                "The domestic cat (Felis catus or Felis silvestris catus) is a small, typically furry, domesticated, and carnivorous mammal. They are often called house cats when kept as indoor pets or simply cats when there is no need to distinguish them from other felids and felines. Cats are often valued by humans for companionship and their ability to hunt vermin.",
                "https://en.wikipedia.org/wiki/Cat"));
        cells.add(new ImageCell("http://i.imgur.com/Hm8ytbj.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/QjofBaf.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/KC0p0zA.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/U22FeMD.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/JGCf1iC.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/H5YoGyZ.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/XQN1MAl.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/yTHq8Yr.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/g6tLR55.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/Nxod2TG.jpg"));

        return cells;
    }

    public List<ITableCell> getDogCells() {
        ArrayList<ITableCell> cells = new ArrayList<>();
        cells.add(new TextCell(
                "The domestic dog (Canis lupus familiaris or Canis familiaris) is a domesticated canid which has been selectively bred for millennia for various behaviors, sensory capabilities, and physical attributes.",
                "https://en.wikipedia.org/wiki/Dog"));
        cells.add(new ImageCell("http://i.imgur.com/wuj4yic.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/DuDD8VS.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/5kaBm2h.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/lnHZYQz.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/hBUcsIa.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/99vDFxD.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/2x1BO8U.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/SMRUKSH.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/UlRY6fH.jpg"));

        return cells;
    }

    public List<ITableCell> getBearCells() {
        ArrayList<ITableCell> cells = new ArrayList<>();
        cells.add(new TextCell(
                "The polar bear (Ursus maritimus) is a carnivorous bear whose native range lies largely within the Arctic Circle, encompassing the Arctic Ocean, its surrounding seas and surrounding land masses. It is a large bear, approximately the same size as the omnivorous Kodiak bear (Ursus arctos middendorffi). A boar (adult male) weighs around 350–700 kg (772–1,543 lb), while a sow (adult female) is about half that size. Although it is the sister species of the brown bear, it has evolved to occupy a narrower ecological niche, with many body characteristics adapted for cold temperatures, for moving across snow, ice, and open water, and for hunting the seals which make up most of its diet. Although most polar bears are born on land, they spend most of their time at sea. Their scientific name means \"maritime bear\", and derives from this fact. Polar bears hunt their preferred food of seals from the edge of sea ice, often living off fat reserves when no sea ice is present.",
                "https://en.wikipedia.org/wiki/Polar_bear"));
        cells.add(new ImageCell("http://i.imgur.com/j5SDeeH.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/3Xkv00y.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/2FaRA3h.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/ja3Zi.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/mN5sQxY.jpg"));
        cells.add(new ImageCell("http://i.imgur.com/ZeWp00L.jpg"));

        return cells;
    }
}
