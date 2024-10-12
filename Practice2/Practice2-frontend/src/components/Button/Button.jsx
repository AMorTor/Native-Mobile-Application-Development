import PropTypes from "prop-types";

export default function Button({ children, className, ...props }) {
  return (
    <button
      className={`bg-indigo-600 text-white rounded-md py-2 px-3 text-sm font-semibold ${className}`}
      {...props}
    >
      {children}
    </button>
  );
}

Button.propTypes = {
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
};
