import { createBrowserRouter } from "react-router-dom";
import Signup from "./Signup";
import Signin from "./Signin";
import Home from "./Home";
import { homeLoader } from "./Home/Home.handlers.js";
import User from "./User/index.js";
import { userAction, userLoader } from "./User/User.handlers.js";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
    loader: homeLoader,
  },
  {
    path: "/user/:id",
    element: <User />,
    loader: userLoader,
    action: userAction,
  },
  {
    path: "/signup",
    element: <Signup />,
  },
  {
    path: "/signin",
    element: <Signin />,
  },
]);
