/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,ts}"],
  theme: {
    extend: {
      colors: {
        obsidian: '#0B0B0B',
        crimson: {
          DEFAULT: '#DC143C',
          hover:   '#B01030',
          light:   '#FF2050',
        },
        surface: {
          100: '#111111',
          200: '#1A1A1A',
          300: '#2A2A2A',
          400: '#3A3A3A',
        },
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', 'sans-serif'],
      },
      boxShadow: {
        crimson: '0 4px 32px rgba(220, 20, 60, 0.25)',
        'crimson-lg': '0 8px 48px rgba(220, 20, 60, 0.35)',
      },
    },
  },
  plugins: [],
};
