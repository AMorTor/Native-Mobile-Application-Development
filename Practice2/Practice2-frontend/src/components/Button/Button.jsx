import { forwardRef } from "react";
import { twMerge } from "tailwind-merge";
import PropTypes from "prop-types";

const Button = forwardRef(({ children, className, ...props }, forwardedRef) => (
  <button
    className={twMerge(
      "bg-indigo-600 hover:opacity-85 text-white rounded-md py-2 px-3 text-sm font-semibold",
      className,
    )}
    {...props}
    ref={forwardedRef}
  >
    {children}
  </button>
));

Button.displayName = "Button";

Button.propTypes = {
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
};

export default Button;
