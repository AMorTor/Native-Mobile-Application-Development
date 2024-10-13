import PropTypes from "prop-types";

export default function Label({ children, className, ...props }) {
  return (
    <label
      className={`font-medium text-sm text-gray-900 ${className}`}
      {...props}
    >
      {children}
    </label>
  );
}

Label.propTypes = {
  children: PropTypes.node.isRequired,
  className: PropTypes.string,
};
