import Navbar from "../Navbar/index.js";
import { Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <div className="h-svh">
      <Navbar />
      <div className="container mx-auto px-4 py-6 sm:px-0">
        <Outlet />
      </div>
    </div>
  );
}
