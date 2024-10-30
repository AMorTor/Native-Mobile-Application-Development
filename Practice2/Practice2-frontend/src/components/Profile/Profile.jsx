import { NavLink, useNavigate } from "react-router-dom";
import * as Popover from "@radix-ui/react-popover";

export default function Profile() {
  const id = localStorage.getItem("userId");
  const navigate = useNavigate();

  function handleSignOut() {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    navigate("/signin");
  }

  return (
    <Popover.Root>
      <Popover.Trigger className="rounded-full border text-gray-600 border-gray-600 hover:text-gray-900 p-1.5">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 24 24"
          strokeWidth={1.5}
          stroke="currentColor"
          className="size-6"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z"
          />
        </svg>
      </Popover.Trigger>
      <Popover.Anchor />
      <Popover.Portal>
        <Popover.Content
          align="end"
          className="text-gray-700 bg-white text-sm shadow py-1 h-fit min-w-[150px]"
        >
          <ul>
            <li>
              <NavLink
                to={`/user/${id}`}
                className="block hover:bg-gray-100 py-2 px-4"
              >
                Mi perfil
              </NavLink>
            </li>
            <li>
              <button
                className="w-full text-left hover:bg-gray-100 py-2 px-4 text-red-500"
                onClick={handleSignOut}
              >
                Cerrar sesión
              </button>
            </li>
          </ul>
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
  );
}
