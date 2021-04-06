## Why do we need this Library in the first place?

Let's take a simple example of showing a collection of Strings in the `RecyclerView`.

We create a view item as below:

`item_food.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>  
<LinearLayout
  xmlns:android="http://schemas.android.com/apk/res/android"  
  xmlns:tools="http://schemas.android.com/tools"  
  android:layout_width="match_parent"  
  android:layout_height="wrap_content"  
  android:padding="5dp">  

  <TextView
    android:id="@+id/value_text_food_name"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textColor="@color/black"
    android:textSize="16sp"
    tools:text="Pizza" />

</LinearLayout>
```
To render this `item_food.xml` in `RecyclerView` and intercept any event in a particular item, we need a `RecyclerView.ViewHolder`.

`FoodViewHolder.kt`

```kotlin
class FoodViewHolder(private val binding: ItemFoodBinding, private val listener: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

  init {
    initialize()
  }

  fun setFoodName(name: String) {
    binding.valueTextFoodName.text = name
  }

  private fun initialize() {
    binding.valueTextFoodName.setOnClickListener {
      listener(adapterPosition)
    }
  }

}
```

Once a `RecyclerView.ViewHolder` is ready, then prepare the `RecyclerView.Adapter` that manages the creation and destruction of Recycled Views.

`FoodAdapter.kt`

```kotlin
class FoodAdapter(private val adapterListener: (String) -> Unit) : RecyclerView.Adapter<FoodViewHolder>() {

  private val list: LinkedList<String> by lazy {
    LinkedList()
  }

  private val vhListener: (Int) -> Unit = { position ->
    adapterListener(list[position])
  }

  override fun getItemCount(): Int = list.size
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder = FoodViewHolder(
    ItemFoodBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    ),
    vhListener
  )

  override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = with(holder) {
    setFoodName(list[position])
  }

  fun setFoods(newList: List<String>) {  
    list.clear()  
    list.addAll(newList)  
    notifyDataSetChanged()  
  }

}
```

Now our RecyclerView is ready to be used, but some setup first:

`activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>  
<androidx.constraintlayout.widget.ConstraintLayout  
  xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".ui.activity.MainActivity" >

  <androidx.recyclerview.widget.RecyclerView
    android:id="@+id/list_food"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

`MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private val adapterListener: (String) -> Unit = { foodName ->
    Toast.makeText(this, foodName, Toast.LENGTH_SHORT).show()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
    setContentView(binding.root)
    setUpViews()
    populateData()
  }

  override fun onDestroy() {
    super.onDestroy()
    cleanUpViews()
  }

  private fun setUpViews() {
    setUpRecyclerView()
  }

  private fun setUpRecyclerView() = with(binding.listFood) {
    layoutManager = LinearLayoutManager(this@MainActivity)
    adapter = FoodAdapter(adapterListener)
  }

  private fun populateData() = with(binding.listFood) {
    (adapter as? FoodAdapter)?.setFoods(provideFoodList())
  }

  private fun provideFoodList(): List<String> = listOf(
    "Hamburger",
    "Pizza",
    "French Fries",
    "Steak",
    ""
  )

  private fun cleanUpViews() {
    cleanUpRecyclerView()
  }

  private fun cleanUpRecyclerView() = with(binding.listFood) {
    adapter = null
    layoutManager = null
  }

}
```

At this point anyone would say this is some pretty standard stuff. This is all rosy if you have got only one collection of items to render in one screen.
If you have got many screen that requires different collection to be rendered, then it is obvious that many tasks in the whole setup is repeating itself.

BTW, list of things that'll repeat (most probable):

 - In a `RecyclerView.ViewHolder`, setting up a method that sets-up the listeners of `Views` under it.
 - In a `RecyclerView.ViewHolder`, setting up a View Accessor.
 - In a `RecyclerView.Adapter`, managing the `list` by adding all the items, clearing all the items and such collection update procedures.
 - In any `View` that encompasses the `RecyclerView`, the set-up and tear-down code.
 - In any `View` that encompasses the `RecyclerView`, the code to set/clear the collection.

And when code is repeating, we can move some setup in the abstract classes.

So, the aim of this library is to reduce the Boiler Plate code for setting-up a `RecyclerView`.
