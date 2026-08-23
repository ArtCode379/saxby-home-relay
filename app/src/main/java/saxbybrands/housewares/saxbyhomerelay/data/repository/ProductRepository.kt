package saxbybrands.housewares.saxbyhomerelay.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import saxbybrands.housewares.saxbyhomerelay.data.model.Product
import saxbybrands.housewares.saxbyhomerelay.data.model.ProductCategory

class ProductRepository {
    private val products =
        listOf(
            Product(
                1,
                "Ember Kettle",
                "A quiet 1.7 litre kettle with temperature control, a washable filter and automatic shut-off for safer everyday brewing.",
                ProductCategory.KITCHEN,
                49.00,
                "https://images.unsplash.com/photo-1594213114663-d94db9b17125?w=1200",
            ),
            Product(
                2,
                "Oakline Toaster",
                "Four extra-wide slots, seven browning levels and a lift-and-look lever in a warm brushed finish.",
                ProductCategory.KITCHEN,
                64.00,
                "https://images.unsplash.com/photo-1583722154774-5ec8c696b931?w=1200",
            ),
            Product(
                3,
                "Halo Table Lamp",
                "Soft diffused light with touch dimming and a compact linen shade made for bedside tables and reading corners.",
                ProductCategory.LIGHTING,
                42.00,
                "https://images.unsplash.com/photo-1507473885765-e6ed057f782c?w=1200",
            ),
            Product(
                4,
                "Nest Storage Set",
                "Three stackable woven baskets that keep shelves calm while letting linens and everyday essentials breathe.",
                ProductCategory.STORAGE,
                38.50,
                "https://images.unsplash.com/photo-1618220179428-22790b461013?w=1200",
            ),
            Product(
                5,
                "Drift Throw",
                "A generously sized recycled-cotton throw with a soft herringbone weave and hand-finished tassels.",
                ProductCategory.COMFORT,
                36.00,
                "https://images.unsplash.com/photo-1583845112203-29329902332e?w=1200",
            ),
            Product(
                6,
                "Swift Hand Vacuum",
                "Cordless spot cleaning with two power modes, crevice tool and a washable fine-dust filter.",
                ProductCategory.CLEANING,
                79.00,
                "https://images.unsplash.com/photo-1558317374-067fb5f30001?w=1200",
            ),
            Product(
                7,
                "Marlow Dinner Set",
                "A 12-piece stoneware service for four, finished with a softly speckled reactive glaze.",
                ProductCategory.KITCHEN,
                58.00,
                "https://images.unsplash.com/photo-1610701596007-11502861dcfa?w=1200",
            ),
            Product(
                8,
                "Arc Floor Lamp",
                "A slim powder-coated frame and directional shade bring focused light without taking over the room.",
                ProductCategory.LIGHTING,
                95.00,
                "https://images.unsplash.com/photo-1540932239986-30128078f3c5?w=1200",
            ),
            Product(
                9,
                "Calm Air Purifier",
                "Three-stage filtration, sleep mode and an air-quality indicator for bedrooms and home offices.",
                ProductCategory.COMFORT,
                129.00,
                "https://images.unsplash.com/photo-1585771724684-38269d6639fd?w=1200",
            ),
            Product(
                10,
                "Linden Shelf Unit",
                "A compact three-tier bamboo shelf for bathrooms, kitchens or hallways, with tool-free assembly.",
                ProductCategory.STORAGE,
                54.00,
                "https://images.unsplash.com/photo-1594620302200-9a762244a156?w=1200",
            ),
            Product(
                11,
                "Steamline Iron",
                "Fast ceramic soleplate, anti-drip control and a precise steam tip for crisp collars and seams.",
                ProductCategory.CLEANING,
                45.00,
                "https://images.unsplash.com/photo-1582735689369-4fe89db7114c?w=1200",
            ),
            Product(
                12,
                "Cloud Cushion Pair",
                "Two supportive feather-alternative cushions in washable textured cotton covers.",
                ProductCategory.COMFORT,
                32.00,
                "https://images.unsplash.com/photo-1584100936595-c0654b55a2e2?w=1200",
            ),
        )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
