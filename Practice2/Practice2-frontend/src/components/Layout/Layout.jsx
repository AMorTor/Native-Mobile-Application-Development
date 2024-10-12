import PropTypes from "prop-types";
import Navbar from "../Navbar/index.js";

export default function Layout({ children }) {
  return (
    <div className="h-svh">
      <Navbar />
      <div className="container mx-auto px-4 py-6 sm:px-0">{children}</div>
    </div>
  );
}

Layout.propTypes = {
  children: PropTypes.node.isRequired,
};
