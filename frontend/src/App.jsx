import { useEffect, useState } from "react";
import { fetchProducts, fetchProductById, addProduct } from "./api";

function App() {
  const [products, setProducts] = useState([]);
  const [singleProduct, setSingleProduct] = useState(null);
  const [productId, setProductId] = useState("");
  const [delay, setDelay] = useState(false);
  const [error, setError] = useState("");

  // form state
  const [newProductName, setNewProductName] = useState("");

  useEffect(() => {
    loadProducts();
  }, [delay]);

  const loadProducts = async () => {
    try {
      const data = await fetchProducts(delay);
      setProducts(data);
      setError("");
    } catch (err) {
      console.error(err);
      setError("Failed to load products");
    }
  };

  const handleSearch = async () => {
    try {
      const data = await fetchProductById(productId, delay);
      setSingleProduct(data);
      setError("");
    } catch (err) {
      console.error(err);
      setError(`Product with id ${productId} not found`);
      setSingleProduct(null);
    }
  };

  const handleAddProduct = async (e) => {
    e.preventDefault();
    if (!newProductName.trim()) return;

    try {
      await addProduct({ productName: newProductName });
      setNewProductName("");
      await loadProducts(); // refresh list
    } catch (err) {
      console.error(err);
      setError("Failed to add product");
    }
  };

  const renderRating = (rating) => {
    if (!rating) return "N/A (0 reviews)";
    if (rating.average === 0 && rating.count === 0) {
      return "⚡ Fallback triggered";
    }
    return `⭐ ${rating.average} (${rating.count} reviews)`;
  };

  return (
    <div style={{ padding: "20px", fontFamily: "Arial" }}>
      <h1>📦 Product Catalog</h1>

      <div style={{ marginBottom: "20px" }}>
        <label>
          <input
            type="checkbox"
            checked={delay}
            onChange={() => setDelay(!delay)}
          />
          Simulate Delay (Triggers Timeout + Fallback)
        </label>
      </div>

      <h2>Add New Product</h2>
      <form onSubmit={handleAddProduct} style={{ marginBottom: "20px" }}>
        <input
          type="text"
          value={newProductName}
          onChange={(e) => setNewProductName(e.target.value)}
          placeholder="Enter product name"
          required
        />
        <button type="submit">Add Product</button>
      </form>

      <h2>All Products</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul>
        {products.map((p) => (
          <li key={p.productId}>
            <b>{p.productName}</b> — {renderRating(p.rating)}
          </li>
        ))}
      </ul>

      <h2>Find Product by ID</h2>
      <input
        type="number"
        value={productId}
        onChange={(e) => setProductId(e.target.value)}
        placeholder="Enter product ID"
      />
      <button onClick={handleSearch}>Search</button>

      {singleProduct && (
        <div style={{ marginTop: "20px" }}>
          <h3>Product Details</h3>
          <p>
            <b>ID:</b> {singleProduct.productId}
          </p>
          <p>
            <b>Name:</b> {singleProduct.productName}
          </p>
          <p>
            <b>Rating:</b> {renderRating(singleProduct.rating)}
          </p>
        </div>
      )}
    </div>
  );
}

export default App;
