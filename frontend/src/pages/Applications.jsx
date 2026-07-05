import { useEffect, useState } from "react";
import { Plus, Trash2, FileText } from "lucide-react";
import Layout from "../components/Layout";
import Modal from "../components/Modal";
import StatusBadge from "../components/StatusBadge";
import api from "../api/client";

const STATUS_OPTIONS = ["APPLIED", "SHORTLISTED", "INTERVIEW_SCHEDULED", "HIRED", "REJECTED"];

export default function Applications() {
  const [applications, setApplications] = useState([]);
  const [candidates, setCandidates] = useState([]);
  const [jobs, setJobs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [form, setForm] = useState({ candidateId: "", jobId: "", coverNote: "" });
  const [error, setError] = useState("");

  const load = () => {
    setLoading(true);
    Promise.all([
      api.get("/applications"),
      api.get("/candidates"),
      api.get("/jobs"),
    ])
      .then(([appsRes, candRes, jobsRes]) => {
        setApplications(appsRes.data);
        setCandidates(candRes.data);
        setJobs(jobsRes.data);
      })
      .catch(() => setError("Could not load applications"))
      .finally(() => setLoading(false));
  };

  useEffect(load, []);

  const openCreate = () => {
    setForm({ candidateId: "", jobId: "", coverNote: "" });
    setError("");
    setModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await api.post("/applications", {
        candidateId: Number(form.candidateId),
        jobId: Number(form.jobId),
        coverNote: form.coverNote,
      });
      setModalOpen(false);
      load();
    } catch (err) {
      setError(err.response?.data?.message || "Something went wrong");
    }
  };

  const handleStatusChange = async (id, status) => {
    await api.patch(`/applications/${id}/status?status=${status}`);
    load();
  };

  const handleDelete = async (id) => {
    if (!confirm("Withdraw this application?")) return;
    await api.delete(`/applications/${id}`);
    load();
  };

  return (
    <Layout>
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-slate-900">Applications</h1>
          <p className="text-slate-500 text-sm mt-1">Track candidates through your pipeline</p>
        </div>
        <button
          onClick={openCreate}
          className="flex items-center gap-2 bg-brand-600 hover:bg-brand-700 text-white text-sm font-medium px-4 py-2.5 rounded-lg transition-colors"
        >
          <Plus size={16} />
          New Application
        </button>
      </div>

      <div className="bg-white rounded-2xl ring-1 ring-slate-200 overflow-hidden">
        {loading ? (
          <p className="text-slate-500 text-sm p-6">Loading applications...</p>
        ) : applications.length === 0 ? (
          <div className="p-10 text-center">
            <FileText size={32} className="mx-auto text-slate-300 mb-3" />
            <p className="text-slate-500 text-sm">No applications yet.</p>
          </div>
        ) : (
          <table className="w-full text-sm">
            <thead className="bg-slate-50 text-slate-500 text-xs uppercase tracking-wide">
              <tr>
                <th className="text-left px-6 py-3 font-medium">Candidate</th>
                <th className="text-left px-6 py-3 font-medium">Job</th>
                <th className="text-left px-6 py-3 font-medium">Cover Note</th>
                <th className="text-left px-6 py-3 font-medium">Status</th>
                <th className="text-right px-6 py-3 font-medium">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {applications.map((a) => (
                <tr key={a.id} className="hover:bg-slate-50">
                  <td className="px-6 py-4 font-medium text-slate-900">{a.candidateName}</td>
                  <td className="px-6 py-4 text-slate-600">{a.jobTitle}</td>
                  <td className="px-6 py-4 text-slate-600 max-w-xs truncate">{a.coverNote || "-"}</td>
                  <td className="px-6 py-4">
                    <select
                      value={a.status}
                      onChange={(e) => handleStatusChange(a.id, e.target.value)}
                      className="text-xs font-medium rounded-lg border border-slate-200 px-2 py-1.5 bg-white focus:outline-none focus:ring-2 focus:ring-brand-500"
                    >
                      {STATUS_OPTIONS.map((s) => (
                        <option key={s} value={s}>
                          {s.replaceAll("_", " ")}
                        </option>
                      ))}
                    </select>
                  </td>
                  <td className="px-6 py-4 text-right">
                    <button
                      onClick={() => handleDelete(a.id)}
                      className="text-slate-400 hover:text-rose-600 p-1.5 rounded-lg hover:bg-rose-50"
                    >
                      <Trash2 size={16} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      <Modal open={modalOpen} onClose={() => setModalOpen(false)} title="New Application">
        <form onSubmit={handleSubmit} className="space-y-4">
          {error && (
            <div className="px-3 py-2 rounded-lg bg-rose-50 text-rose-600 text-sm">{error}</div>
          )}
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1.5">Candidate</label>
            <select
              required
              value={form.candidateId}
              onChange={(e) => setForm({ ...form, candidateId: e.target.value })}
              className="w-full px-3 py-2.5 rounded-lg border border-slate-300 text-sm focus:outline-none focus:ring-2 focus:ring-brand-500 bg-white"
            >
              <option value="">Select a candidate</option>
              {candidates.map((c) => (
                <option key={c.id} value={c.id}>
                  {c.fullName} ({c.email})
                </option>
              ))}
            </select>
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1.5">Job</label>
            <select
              required
              value={form.jobId}
              onChange={(e) => setForm({ ...form, jobId: e.target.value })}
              className="w-full px-3 py-2.5 rounded-lg border border-slate-300 text-sm focus:outline-none focus:ring-2 focus:ring-brand-500 bg-white"
            >
              <option value="">Select a job</option>
              {jobs.map((j) => (
                <option key={j.id} value={j.id}>
                  {j.title}
                </option>
              ))}
            </select>
          </div>
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-1.5">Cover note</label>
            <textarea
              rows={3}
              value={form.coverNote}
              onChange={(e) => setForm({ ...form, coverNote: e.target.value })}
              className="w-full px-3 py-2.5 rounded-lg border border-slate-300 text-sm focus:outline-none focus:ring-2 focus:ring-brand-500"
            />
          </div>

          <button
            type="submit"
            className="w-full bg-brand-600 hover:bg-brand-700 text-white font-medium py-2.5 rounded-lg transition-colors"
          >
            Submit application
          </button>
        </form>
      </Modal>
    </Layout>
  );
}
