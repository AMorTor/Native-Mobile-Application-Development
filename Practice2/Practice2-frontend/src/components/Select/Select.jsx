import PropTypes from "prop-types";

export default function Select({ children, className, ...props }) {
  return (
    <select
      className={`max-w-xs py-1.5 px-3 border rounded-md text-sm text-gray-900 shadow-sm outline-indigo-600 ${className}`}
      {...props}
    >
      {children}
    </select>
  );
}

Select.propTypes = {
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
};
