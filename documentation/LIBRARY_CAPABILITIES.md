### What does this Library Offer?

Here are some classes offered:

 - `BaseViewHolder.kt`: An Abstract `RecyclerView.ViewHolder` which is life-cycle aware, and contains events that notifies when the `RecyclerView.ViewHolder` is displayed/removed from the `RecyclerView`.
 - `BaseSingleVHAdapter.kt`: An Abstract `RecyclerView.Adapter` which renders 1-Type of `RecyclerView.ViewHolder`.
 - `BaseMultipleVHAdapter.kt`: An Abstract `RecyclerView.Adapter` which renders n-Type of `RecyclerView.ViewHolder`.
 - `Utility.kt`: Kotlin File containing various Utility Extension Methods around `RecyclerView` and some `typealias` to help with intercepting events from the `RecyclerView.ViewHolder` and `RecyclerView.Adapter`.

Continuing on the [previous example](NEED_OF_THIS_LIBRARY.md), we can change the following files with the help of this Library:

`FoodViewHolder.kt`

```kotlin
class FoodViewHolder(binding: ItemFoodBinding, private val listener: ViewHolderListener) :
  BaseViewHolder<ItemFoodBinding>(binding, binding.root) {

  override fun initializeComponents() = with(viewAccessor) {
    valueTextFoodName.setOnClickListener {
      listener(adapterPosition)
    }
  }

  override fun cleanUp() = with(viewAccessor) {
    valueTextFoodName.setOnClickListener(null)
  }

  fun setFoodName(name: String) = with(viewAccessor) {
    valueTextFoodName.text = name
  }

}
```

With the method `initializeComponents()` you can set-up the `View` in the `RecyclerView.ViewHolder` conveniently as this method will trigger only when this specific `BaseViewHolder` is being displayed.

The method `cleanUp()` helps to clean any references that are held by the view in this `BaseViewHolder`. This method will be triggered when this `BaseViewHolder` is no longer visible. This method is especially helpful when you are rendering something complex as a Video Stream.

You can also notice that there is a `viewAccessor` which is basically used to access that view in the `BaseViewHolder`. This `viewAccessor` is agnostic to any kind of View Accessing Implementation. In the above example, I'm using 
[ViewBinding](https://developer.android.com/topic/libraries/view-binding) with `BaseViewHolder`. You can also use [DataBinding](https://developer.android.com/topic/libraries/data-binding) as well.

`FoodAdapter.kt`

```kotlin
class FoodAdapter(private val adapterListener: AdapterListener<String>) :
  BaseSingleVHAdapter<String, FoodViewHolder>() {

  private val vhListener: ViewHolderListener = { position ->
    adapterListener(list[position])
  }

  override fun provideViewHolder(parent: ViewGroup): FoodViewHolder = FoodViewHolder(
    ItemFoodBinding.inflate(
      LayoutInflater.from(parent.context),
      parent,
      false
    ),
    vhListener
  )

  override fun onBind(viewHolder: FoodViewHolder, position: Int) = with(viewHolder) {
    setFoodName(list[position])
  }

}
```

As you can notice, the process to manage `list` is moved up to both `BaseSingleVHAdapter` and `BaseMultipleVHAdapter`.

You have these methods provided by these adapters:

 - `fun addToList(newList: List<Model>)`: Appends the items of `newList` in the already rendered `list`.
 - `fun replaceList(newList: List<Model>)`: Clears the content of `list`, then add all the contents of `newList` in it, which in essence replacing the previous contents.
 - `fun clearAllItems()`: Clears all the contents of `list` and renders an empty view.

The methods above are the most common use-cases to be thought of. If you want to manage the `list` anyway yourselves, make a method as the `list` has protected `access` and is accessible to any class that extends to `BaseSingleVHAdapter` or `BaseMultipleVHAdapter`.
However, since this `list` is `protected`, so it can't be accessed outside of it. If you want a currently rendered list from this adapter, use `immutableList` instead.

In the class generic parameter of `BaseSingleVHAdapter` and `BaseMultipleVHAdapter`, you can provide any `RecyclerView.ViewHolder` (including any `BaseViewHolder`, since it extends to `RecyclerView.ViewHolder`!). 

Okay!
Now about the set-up of this Adapter in the view:

`MainActivity.kt`

```kotlin
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private val adapterListener: AdapterListener<String> = { foodName ->
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
    binding.listFood.initialize(FoodAdapter(adapterListener), LinearLayoutManager(this))
  }

  private fun populateData() = binding.listFood.addItems(provideFoodList(), false)

  private fun provideFoodList(): List<String> = listOf(
    "Hamburger",
    "Pizza",
    "French Fries",
    "Steak",
    ""
  )

  private fun cleanUpViews() = binding.listFood.cleanUp()

}
```

This library provides some extension functions around RecyclerView:

 - `fun RecyclerView.initialize(...)`: Helps initialising the `RecyclerView` with `RecyclerView.Adapter`, `RecyclerView.LayoutManager` and such components.
 - `fun RecyclerView.addItems()`: Helps Populating the `List` of `Model` in this `RecyclerView`.
 - `fun RecyclerView.clearItems()`: Helps Clearing the rendered List in this `RecyclerView`.
 - `fun RecyclerView.cleanUp()`: Helps Clearing out any held references in this `RecyclerView` to avoid Memory Leaks.

NOTE: The methods `RecyclerView.addItems()` and `RecyclerView.clearItems()` only works when the adapter supplied to this `RecyclerView` extends to either `BaseSingleVHAdapter` or `BaseMultipleVHAdapter`. For adapter extending other implementation of `RecyclerView.Adapter`, these methods would not work.

NOTE: The methods `RecyclerView.addItems()` and `RecyclerView.clearItems()` are designed in such a way that UI Frames are not skipped, resulting in smoother View Effects.

As you can also notice, there are these two `typealias`es:

 - `typealias ViewHolderListener = (Int) -> Unit`: A General Purpose event notifier from `RecyclerView.ViewHolder` to the `RecyclerView.Adapter`.
 - `typealias AdapterListener<Model> = (Model) -> Unit`: A General Purpose event notifier from `RecyclerView.Adapter`  to the view encompassing the `RecyclerView` itself.
