import { createBrowserRouter } from "react-router-dom";
import Signup from "./Signup/index.js";
import { signUpAction } from "./Signup/Signup.handlers.js";

export const router = createBrowserRouter([
  {
    path: "/signup",
    element: <Signup />,
    action: signUpAction,
  },
]);
