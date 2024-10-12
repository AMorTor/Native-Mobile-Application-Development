import { createBrowserRouter } from "react-router-dom";
import Signup from "./Signup";
import Signin from "./Signin";

export const router = createBrowserRouter([
  {
    path: "/signup",
    element: <Signup />,
  },
  {
    path: "/signin",
    element: <Signin />,
  },
]);
