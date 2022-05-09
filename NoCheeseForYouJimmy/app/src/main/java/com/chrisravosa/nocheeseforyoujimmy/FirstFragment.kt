package com.chrisravosa.nocheeseforyoujimmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chrisravosa.nocheeseforyoujimmy.databinding.FragmentFirstBinding
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    lateinit var sceneView: ArSceneView
    lateinit var modelNode: ArModelNode

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sceneView = view.findViewById(R.id.sceneView)

        binding.buttonFirst.setOnClickListener {
            anchorFridge()
        }

        modelNode = ArModelNode(placementMode = PlacementMode.BEST_AVAILABLE).apply {
            loadModelAsync(
                context = requireContext(),
                glbFileLocation = "https://storage.googleapis.com/ar-answers-in-search-models/static/Tiger/model.glb",
                lifecycle = lifecycle,
                autoAnimate = true,
                autoScale = true,
                // Place the model origin at the bottom center
                centerOrigin = Position(x = 0.0f, y = 0.0f, z = 0.0f)
            )
        }
        sceneView.addChild(modelNode)
    }

    private fun anchorFridge() {
        modelNode.anchor()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}