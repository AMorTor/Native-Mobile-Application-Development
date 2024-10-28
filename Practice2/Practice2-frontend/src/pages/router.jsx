import { createBrowserRouter } from "react-router-dom";
import Signup from "./Signup";
import { signUpAction } from "./Signup/Signup.handlers.js";
import Signin from "./Signin";
import { signInAction } from "./Signin/Signin.handlers.js";
import Home from "./Home";
import { homeLoader } from "./Home/Home.handlers.js";
import User from "./User/index.js";
import { userAction, userLoader } from "./User/User.handlers.js";
import NewUser from "./NewUser/index.js";
import { newUserAction } from "./NewUser/NewUser.handlers.js";
import Layout from "../components/Layout/index.js";
import { layoutLoader } from "../components/Layout/Layout.handlers.js";
import ErrorBoundary from "../components/Layout/LayoutErrorBoundary.jsx";

export const router = createBrowserRouter([
  {
    path: "/signup",
    element: <Signup />,
    action: signUpAction,
    errorElement: <ErrorBoundary />,
  },
  {
    path: "/signin",
    element: <Signin />,
    action: signInAction,
    errorElement: <ErrorBoundary />,
  },
  {
    path: "*",
    element: <Layout />,
    loader: layoutLoader,
    errorElement: <ErrorBoundary />,
    children: [
      {
        path: "",
        element: <Home />,
        loader: homeLoader,
      },
      {
        path: "user",
        element: <NewUser />,
        action: newUserAction,
      },
      {
        path: "user/:id",
        element: <User />,
        loader: userLoader,
        action: userAction,
      },
    ],
  },
]);
