import { useState } from 'react';
export default function useApi() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const run = async (request) => {
    setLoading(true); setError('');
    try { return await request(); }
    catch (e) { setError(e.response?.data?.message || 'Something went wrong. Please try again.'); throw e; }
    finally { setLoading(false); }
  };
  return { loading, error, setError, run };
}
