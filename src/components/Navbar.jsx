import "../styles/Dashboard.css";

function Navbar() {
  return (
    <div className="navbar">

      <input
        type="text"
        placeholder="Search..."
        className="search-bar"
      />

      <div className="profile">
        👤
      </div>

    </div>
  );
}

export default Navbar;