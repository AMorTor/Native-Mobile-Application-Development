import Profile from "../Profile";
import { NavLink } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="p-4 shadow">
      <ul className="container flex gap-8 justify-between items-center text-gray-900 mx-auto">
        <li className="text-sm font-semibold">
          {/* TODO: Redirect to a page according to the user rol*/}
          <NavLink to="/" className="hover:underline">
            Inicio
          </NavLink>
        </li>
        <li className="text-sm font-semibold">
          <Profile />
        </li>
      </ul>
    </nav>
  );
}
