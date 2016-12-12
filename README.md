MultiAdapter
===
A small libray help you ceate multiple type ViewHolders in RecyclerView

Why
===
Beasue define TYPE is boring

HOW
===
#### dependencies
[https://jitpack.io/#imlunacat/MultiAdapter][1]

#### the code
```java
RecyclerView rv = (RecyclerView) findViewById(R.id.activity_recyclerview);
rv.setLayoutManager(new LinearLayoutManager(this));
List<ITableCell> items = getCell();
MultiAdapter<ITableCell> adapter = new MultiAdapter<>();
adapter.addAll(items);

adapter.add(TextCell.class,
    TextCellViewHolder.class,
    R.layout.item_text,
    new OnItemClickListener<TextCell>() {
        @Override
        public void onItemClick(TextCell item, int position) {
            Toast.makeText(MainActivity.this, item.getText(), Toast.LENGTH_SHORT).show();
        }
    });
adapter.add(ImageCell.class,
    ImageCellViewHolder.class,
    R.layout.item_image,
    new OnItemClickListener<ImageCell>() {
        @Override
        public void onItemClick(ImageCell item, int position) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getPageUrl()));
            startActivity(browserIntent);
        }
    });
rv.setAdapter(adapter);
```

(see full code in [sample][2])
MultipleAdapter use reflection to reduce boilerplate.
Proguard
===
```
-keepclassmembers class * extends me.lunacat.multiadapter.MultiViewHolder {
    public <init>(...);
}
```

[1]: https://jitpack.io/#imlunacat/MultiAdapter
[2]: https://github.com/imlunacat/MultiAdapter/blob/master/app/src/main/java/me/lunacat/multiadapteractivity/MainActivity.java
